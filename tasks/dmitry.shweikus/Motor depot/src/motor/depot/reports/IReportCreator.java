/**
 * 
 */
package motor.depot.reports;

import motor.depot.clientserver.server.scenario.tables.ITableProvider;

/**
 * @author dima
 *
 */
public interface IReportCreator
{
	byte[] build(ITableProvider provider,String tableName);
}
