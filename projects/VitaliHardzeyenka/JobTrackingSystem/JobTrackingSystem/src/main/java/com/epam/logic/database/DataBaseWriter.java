package com.epam.logic.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import com.epam.logic.Logger;

/**
 * Changer information for data base.
 * Class used for INSERT, UPDATE, DELETE requests.
 * @author Gordeenko
 */
public class DataBaseWriter extends DataBaseOpener {

	private static final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(DataBaseWriter.class.getName()));
	
	public DataBaseWriter() {
		super();
	}
	
    /**
     * Insert a string into table.
     * @param tableName - name of the table, that come be insert into.
     * @param values - values for the fields.
     */
    public boolean insert(String tableName, Object ... values) throws SQLException {
    	ArrayList<Object> objectsList = new ArrayList<>();
    	
    	objectsList.addAll(Arrays.asList(values));
    	
    	return insert(tableName, objectsList);
    }

    /**
     * Insert a string into table.
     * @param tableName - name of the table, that come be insert into.
     * @param values - values for the fields.
     * @return true if action has been committed, else false.
     * @throws SQLException
     */
    public boolean insert(String tableName, ArrayList<Object> values) throws SQLException {
        PreparedStatement insert = null;

        // Create request.
        String insertQuery = String.format("INSERT INTO %s VALUES (NULL, ", tableName); // NULL is for auto-incremental field.
        for (int i = 0; i < values.size() - 1; i ++) {
            insertQuery = insertQuery.concat("?, ");
        }
        insertQuery = insertQuery.concat("?");
        insertQuery = insertQuery.concat(");");

        // Fill the request by values.
        try {
            super.connection.setAutoCommit(false);
            insert = super.connection.prepareStatement(insertQuery);

            // Set the values into the request.
            for (int i = 0; i < values.size(); i++) {
                insert.setObject(i + 1, values.get(i));
            }

            insert.executeUpdate();
            super.connection.commit();
            return true;

        } catch (SQLException exception) {
        	logger.getExceptionTextFileLogger().error(exception);
            System.out.println("Cant insert into database");

            if (super.connection != null) {
                try {
                	logger.getExceptionTextFileLogger().info("Transaction is being rolled back");
                    System.err.print("Transaction is being rolled back");
                    super.connection.rollback();
                } catch(SQLException excep) { }
            }
            return false;
        } finally {
            if (insert != null) {
                insert.close();
            }
            super.connection.setAutoCommit(true);
        }
    }

    /**
     * Delete line from specified table in data base.
     * @param tableName - table from which to delete.
     * @param fieldsConditions - first half of this list is the fields names, the second one - the conditions.
     * 		      			     For example: ("field1", "field2", condition1, condition2) means (field1 = condition1, field2 = condition2).
     * @return true if line has been deleted, else false.
     * @throws SQLException
     */
    public boolean delete(String tableName, Object ... fieldsConditions) throws SQLException {

        PreparedStatement delete = null;

        // Create the query.
        String deleteQuery = String.format("DELETE FROM %s WHERE ", tableName);

        // Fill fields names.
        for (int i = 0; i < (int)fieldsConditions.length / 2; i++) {
            if ((fieldsConditions.length > 2) && (i != 0))  {
                deleteQuery = deleteQuery.concat(" and");
            }
            deleteQuery = deleteQuery.concat(String.format(" %s = ?", fieldsConditions[i]));
        }
        deleteQuery = deleteQuery.concat(";");

        try {
        	super.connection.setAutoCommit(false);

            // Fill conditions.
            delete = super.connection.prepareStatement(deleteQuery);
            for (int i = (int)fieldsConditions.length / 2, j = 0; i < fieldsConditions.length; i++, j++) {
                delete.setObject(j + 1, fieldsConditions[i]);
            }

            delete.executeUpdate();
            super.connection.commit();
            return true;
        } catch (SQLException ex) {
        	// TODO logger
            ex.printStackTrace();
            System.out.println("Cant delete from database");

            if (super.connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    super.connection.rollback();
                } catch(SQLException excep) { }
            }
            return false;
        } finally {
            if (delete != null) {
                delete.close();
            }
            super.connection.setAutoCommit(true);
        }
    }

    /**
     * Update certain values in the tables.
     * @return
     */
    public boolean update(String query) {
    	Statement update;
    	try {
    		super.connection.setAutoCommit(false);
            update = super.connection.createStatement();

            update.executeUpdate(query);

            return true;
        }  catch (SQLException exception) {
        	exception.printStackTrace();
        	// TODO logger
            System.out.println("Can`t update.");

            if (super.connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    super.connection.rollback();
                } catch(SQLException excep) {
                	// TODO logger
                }
            }
            return false;
        } finally {        	
            try {
            	super.connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}