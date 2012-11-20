/**
 * 
 */
package motor.depot.clientserver.server.scenario.tables;

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
}
