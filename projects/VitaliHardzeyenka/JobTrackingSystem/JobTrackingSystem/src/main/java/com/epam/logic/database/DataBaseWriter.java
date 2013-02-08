package com.epam.logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Changer information for data base.
 * Class used for INSERT, UPDATE, DELETE requests.
 * @author Gordeenko
 */
public class DataBaseWriter extends DataBaseOpener {

    /**
     * Insert a string into table.
     * @param connection - connection to the database
     * @param tableName - name of the table, that come be insert into.
     * @param values - values for the fields.
     */
    public boolean insert(Connection connection, String tableName, Object ... values) throws SQLException {
    	ArrayList<Object> objectsList = new ArrayList<>();
    	
    	for (Object value : values) {
    		objectsList.add(value);
    	}
    	
    	return insert(connection, tableName, objectsList);
    }

    /**
     * Insert a string into table.
     * @param connection - connection to the database.
     * @param tableName - name of the table, that come be insert into.
     * @param values - values for the fields.
     * @return true if action has been committed, else false.
     * @throws SQLException
     */
    public boolean insert(Connection connection, String tableName, ArrayList<Object> values) throws SQLException {
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
            connection.setAutoCommit(false);
            insert = connection.prepareStatement(insertQuery);

            // Set the values into the request.
            for (int i = 0; i < values.size(); i++) {
                insert.setObject(i + 1, values.get(i));
            }

            insert.executeUpdate();
            connection.commit();
            return true;

        } catch (SQLException ex) {
        	// TODO logger
            System.out.println("Cant insert into database");

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException excep) { }
            }
            return false;
        } finally {
            if (insert != null) {
                insert.close();
            }
            connection.setAutoCommit(true);
        }
    }

    /**
     * Delete line from specified table in data base.
     * @param connection - connection to the data base.
     * @param tableName - table from which to delete.
     * @param fieldsConditions - first half of this list is the fields names, the second one - the conditions.
     * 		      			     For example: ("field1", "field2", condition1, condition2) means (field1 = condition1, field2 = condition2).
     * @return true if line has been deleted, else false.
     * @throws SQLException
     */
    public boolean delete(Connection connection, String tableName, Object ... fieldsConditions) throws SQLException {

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
            connection.setAutoCommit(false);

            // Fill conditions.
            delete = connection.prepareStatement(deleteQuery);
            for (int i = (int)fieldsConditions.length / 2, j = 0; i < fieldsConditions.length; i++, j++) {
                delete.setObject(j + 1, fieldsConditions[i]);
            }

            delete.executeUpdate();
            connection.commit();
            return true;
        } catch (SQLException ex) {
        	// TODO logger
            ex.printStackTrace();
            System.out.println("Cant delete from database");

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException excep) { }
            }
            return false;
        } finally {
            if (delete != null) {
                delete.close();
            }
            connection.setAutoCommit(true);
        }
    }

    /**
     * Update certain values in the tables.
     * @param _connection
     * @return
     */
    public boolean update(Connection connection, String query) {
    	Statement update;
    	try {
    		connection.setAutoCommit(false);
            update = connection.createStatement();

            update.executeUpdate(query);

            return true;
        }  catch (SQLException exception) {
        	exception.printStackTrace();
        	// TODO logger
            System.out.println("Can`t update.");

            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch(SQLException excep) {
                	// TODO logger
                }
            }
            return false;
        } finally {        	
            try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}