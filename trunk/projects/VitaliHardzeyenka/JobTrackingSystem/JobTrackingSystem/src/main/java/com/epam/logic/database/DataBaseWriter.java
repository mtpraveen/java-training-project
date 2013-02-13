package com.epam.logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Iterator;
import com.epam.logic.Logger;

/**
 * Changer information for data base.
 * Class used for INSERT, UPDATE, DELETE requests.
 * @author Gordeenko
 */
public class DataBaseWriter extends AbstractDataBaseWorker {

	private static final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(DataBaseWriter.class.getName()));

	public DataBaseWriter() {
		super();
	}

	public boolean insert(String tableName, Object ... values) throws SQLException, ClassNotFoundException {
		ArrayList<Object> objectsList = new ArrayList<>();
		objectsList.addAll(Arrays.asList(values));
		return insert(tableName, objectsList);
	}

	/**
	 * Insert a string into table.
	 * @param tableName - name of the table, that come be insert into.
	 * @param values - values for the fields.
	 * @return true if action has been committed, else false.
	 * @throws ClassNotFoundException 
	 * @throws SQLException
	 */
	public boolean insert(String tableName, ArrayList<Object> values) throws ClassNotFoundException, SQLException {
		Connection connection = super.getPool().createConnection();
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
			logger.getExceptionTextFileLogger().error("Cant insert into database! " + ex);
			if (connection != null) {
				try {
					logger.getExceptionTextFileLogger().error("Transaction is being rolled back");
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
	 * Update specified values in the tables.
	 * @return
	 */
	public boolean update(String tableName, 
			Map<String, Object> conditions, 
			Map<String, Object> whereCon) {
		Connection connection = super.getPool().createConnection();
		String query = "UPDATE %s SET %s WHERE %s;";
		
		// Conditions part
		Set set = conditions.entrySet();
		Iterator iterator= set.iterator();
		int i = 0;
		String conditionsPart = "";
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if (i < conditions.size() - 1) {
				conditionsPart = conditionsPart.concat(entry.getKey() + " = '" + entry.getValue() + "',");
			} else {
				conditionsPart = conditionsPart.concat(entry.getKey() + " = '" + entry.getValue() + "'");
			}
			i++;
		}
		
		// WHERE part
		String wherePart = "";
		set = whereCon.entrySet();
		iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Entry) iterator.next();
			wherePart = wherePart.concat(entry.getKey() + " = '" + entry.getValue() + "'");
		}
		
		query = String.format(query, tableName, conditionsPart, wherePart);
		System.out.println(query);
		Statement update = null;
		try {
			connection.setAutoCommit(false);
			update = connection.createStatement();
			update.executeUpdate(query);
			return true;
		}  catch (SQLException exception) {
			logger.getExceptionTextFileLogger().error("Can`t update! " + exception);
			if (connection != null) {
				try {
					connection.rollback();
					logger.getExceptionTextFileLogger().error("Transaction is being rolled back");
				} catch(SQLException ex) {
					logger.getExceptionTextFileLogger().error(ex);
				}
			}
			return false;
		} finally {        	
			try {
				connection.setAutoCommit(true);
			} catch (SQLException exception) {
				logger.getExceptionTextFileLogger().error(exception);
			}
		}
	}

}