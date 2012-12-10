/**
 * 
 */
package motor.depot.storages.interfaces;

import motor.depot.clientserver.server.ClientThread;

/**
 * @author dima
 *
 */
public interface ITableRowProvider
{
	int getColCount();
	String getColName(int col);
	String getValue(int col);
	String getTranslatedColumn(int col,ClientThread thread);
}
