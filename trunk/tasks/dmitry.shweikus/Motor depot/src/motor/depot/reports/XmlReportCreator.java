/**
 * 
 */
package motor.depot.reports;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import motor.depot.clientserver.server.scenario.tables.ITableProvider;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * @author dima
 * 
 */
public class XmlReportCreator implements IReportCreator
{

	@Override
	public byte[] build(ITableProvider provider, String tableName)
	{
		DocumentFactory factory = DocumentFactory.getInstance();
		Document doc = factory.createDocument();
		Element root = doc.addElement("data");
		for (int row = 0; row < provider.getRowCount(); row++)
		{
			Element e = factory.createElement(tableName);
			for (int col = 0; col < provider.getColCount(); col++)
			{
				e.addAttribute(provider.getColName(col), provider.getCellValue(row, col));
			}
			root.add(e);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		XMLWriter writer;
		try
		{
			writer = new XMLWriter(outputStream, OutputFormat.createPrettyPrint());
			writer.write(doc);
		} catch (UnsupportedEncodingException e)
		{
		} catch (IOException e)
		{
		}
		return outputStream.toByteArray();
	}

}
