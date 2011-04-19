/**
 * 
 */
package autobase.server.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Alexander Nikoniuk
 *<p>Database configuration manager </p>
 * @see DBConnector
 */
public class ConfigurationManager {
	private static ConfigurationManager instance;
	
	private static String username;
	private static String password;
	private static String serverURL;
	private static String dbName;
	private static String driverName;
	private static DatasourceType dsType;

	private ConfigurationManager() throws IOException {
		Properties props = new Properties();
        props.load(new FileInputStream("server.properties"));
        
        dsType = DatasourceType.valueOf(props.getProperty("datasource").toUpperCase()); 
        
        if (dsType == DatasourceType.DB) {	
			driverName = props.getProperty("driver");
			serverURL = props.getProperty("server");
			dbName = props.getProperty("database");
			username = props.getProperty("login");
			password = props.getProperty("password");	
        }
	}
	
	/**
	 * @return the dbType
	 */
	public static DatasourceType getDsType() {
		return dsType;
	}

	/**
	 * @return the driverName
	 */
	public static String getDriverName() {
		return driverName;
	}

	/**
	 * @return the login
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}

	/**
	 * @return the serverURL
	 */
	public static String getServerURL() {
		return serverURL;
	}

	/**
	 * @return the dbName
	 */
	public static String getDbName() {
		return dbName;
	}
	
	/**
	 * @return the getDbUrl
	 */
	public static String getDbUrl() {
		return "jdbc:mysql://" + serverURL + "/" + dbName;
	}

	public static ConfigurationManager getInstance() {
		if(instance == null)
			try {
				instance = new ConfigurationManager();
			} catch (IOException e) {
				throw new RuntimeException("Unable to load server configuration");
			}
		return instance;	
	}

}
