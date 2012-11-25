/**
 * 
 */
package motor.depot.reports;

import java.util.ArrayList;

import motor.depot.clientserver.server.scenario.tables.ITableProvider;
import motor.depot.storages.csv.utils.CsvBuilder;

/**
 * @author dima
 *
 */
public class CsvReportCreator implements IReportCreator
{

	@Override
	public byte[] build(ITableProvider provider,String tableName)
	{
		CsvBuilder csvBuilder = new CsvBuilder();
		StringBuilder builder = new StringBuilder();
		ArrayList<String> caption = new ArrayList<String>();
		for(int i = 0;i<provider.getColCount();i++)
		{
			caption.add(provider.getColName(i));
		}
		builder.append(csvBuilder.create(caption)).append("\r\n");
		for(int row = 0;row<provider.getRowCount();row++)
		{
			ArrayList<String> rowStrings = new ArrayList<String>();
			for(int col=0;col<provider.getColCount();col++)
			{
				rowStrings.add(provider.getCellValue(row, col));
			}
			builder.append(csvBuilder.create(rowStrings)).append("\r\n");
		}
		return builder.toString().getBytes();
	}

}
