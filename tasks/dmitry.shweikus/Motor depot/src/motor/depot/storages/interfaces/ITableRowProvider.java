/**
 * 
 */
package motor.depot.storages.interfaces;

/**
 * @author dima
 *
 */
public interface ITableRowProvider
{
	int getColCount();
	String getColName(int col);
	String getValue(int col);
}
