/**
 * 
 */
package motor.depot.clientserver.server.scenario.tables;

import motor.depot.clientserver.server.ClientThread;

/**
 * @author dima
 *
 */
public interface ITableProvider
{
	int getColCount();
	int getRowCount();
	String getColName(int col); 
	String getCellValue(int row, int col); 
	String getTranslatedColumn(int col,ClientThread thread);
}
