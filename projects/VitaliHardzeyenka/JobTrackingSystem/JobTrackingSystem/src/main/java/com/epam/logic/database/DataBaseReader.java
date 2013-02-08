package com.epam.logic.database;

import java.sql.*;

import com.epam.logic.Logger;

/**
 * Reader of data base. Class describe the SELECT request.
 * @author Gordeenko
 */
public class DataBaseReader extends DataBaseOpener {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(DataBaseReader.class.getName())); 

    /**
     * Selection all elements of string by the conditions.
     * @param connection - data base connection.
     * @param fieldsConditions - first half of this list is the fields names, the second one - the conditions.
     * For example: ("field1", "field2", condition1, condition2) means (field1 = condition1, field2 = condition2)
     * @return - return the set of query result operation.
     */
    public ResultSet select(Connection connection, String tableName, Object ... fieldsConditions) throws SQLException {
        PreparedStatement select = null;
        String selectQuery = null;
        ResultSet resultSet = null;
        int fieldsConditionsNamesEnd = fieldsConditions.length / 2;

        try {
            connection.setAutoCommit(false); 

            // Create the request.
            selectQuery = String.format("SELECT * FROM %s WHERE ", tableName);

            // Fill fields names.
            for (int i = 0; i < fieldsConditionsNamesEnd; i++) {
                if ((fieldsConditions.length > 2) && (i != 0))  {
                    selectQuery = selectQuery.concat(" and");
                }
                selectQuery = selectQuery.concat(String.format(" %s = ?", fieldsConditions[i]));
            }
            selectQuery = selectQuery.concat(";");

            // Fill the conditions.
            select = connection.prepareStatement(selectQuery);
            for (int i = fieldsConditionsNamesEnd, j = 0; i < fieldsConditions.length; i++, j++) {
                select.setObject(j + 1, fieldsConditions[i]);
            }

            // Execute query.
            resultSet = select.executeQuery();
            connection.commit();

            return resultSet;
        } catch (SQLException exception) {
        	logger.getExceptionTextFileLogger().error(exception);
            if (connection != null) {
                try {
                	logger.getExceptionTextFileLogger().info("Transaction is being rolled back");
                    connection.rollback();
                    if (select != null) {
                    	select.close();
                    }
                    resultSet.close();
                } catch(SQLException e) {
                	logger.getExceptionTextFileLogger().error(e);
                }
            }

            return null;
        } finally {        	
            connection.setAutoCommit(true);
        }
    }
    
    /**
     * Selection elements from data base by the request.
     * @param connection - connection to the data base.
     * @param request - full request.
     * @return result set of request.
     */
    public ResultSet select(Connection connection, String request) {
    	ResultSet resultSet = null;
    	try {
            Statement statement;
            statement = connection.createStatement();

            resultSet = statement.executeQuery(request);

            return resultSet;
        }  catch (SQLException exception) {
        	logger.getExceptionTextFileLogger().error("Can`t select from the database." + exception);

            if (connection != null) {
                try {
                	logger.getExceptionTextFileLogger().info("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException e) {
                	logger.getExceptionTextFileLogger().error(e);
                }
            }

            return null;
        } finally {        	
            try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				logger.getExceptionTextFileLogger().error(e);
			}
        }
    }
    
}