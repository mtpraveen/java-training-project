/**
 * 
 */
package motor.depot.clientserver;

/**
 * @author dima
 * kind for data, that can be received from client 
 */
public enum CLIENT_CONTENT_KIND
{
	/**
	 * simple text
	 */
	TEXT, 
	/**
	 * simple text, entered by *-chars
	 */
	PASSWORD,
	/**
	 * binary file
	 */
	FILE
}
