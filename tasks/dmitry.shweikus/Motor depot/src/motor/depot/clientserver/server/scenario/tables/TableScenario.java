/**
 * 
 */
package motor.depot.clientserver.server.scenario.tables;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;

/**
 * @author dima
 * 
 */
public class TableScenario extends AbstractScenario
{
	String caption = "";
	boolean selectable = false;
	boolean waitable = true;
	/**
	 * @return the waitable
	 */
	public boolean isWaitable()
	{
		return waitable;
	}

	/**
	 * @param waitable the waitable to set
	 */
	public void setWaitable(boolean waitable)
	{
		this.waitable = waitable;
	}

	ITableProvider tableProvider;
	int selectedRow = -1;

	/**
	 * @return the caption
	 */
	public String getCaption()
	{
		return caption;
	}

	/**
	 * @return the selectedRow
	 */
	public int getSelectedRow()
	{
		return selectedRow;
	}

	/**
	 * @param caption
	 *            the caption to set
	 */
	public void setCaption(String caption)
	{
		this.caption = caption;
	}

	/**
	 * @return the selectable
	 */
	public boolean isSelectable()
	{
		return selectable;
	}

	/**
	 * @param selectable
	 *            the selectable to set
	 */
	public void setSelectable(boolean selectable)
	{
		this.selectable = selectable;
	}

	/**
	 * @param thread
	 */
	public TableScenario(ClientThread thread, ITableProvider tableProvider) {
		super(thread);
		this.tableProvider = tableProvider;
	}

	private int getColumnWidth(int col)
	{
		int res = tableProvider.getColName(col).length();
		for (int row = 0; row < tableProvider.getRowCount(); row++)
		{
			String value = tableProvider.getCellValue(row, col);
			res = Math.max(res, value.length());
		}
		return res;
	}

	private String padding(String source, int needed, char ch)
	{
		StringBuilder builder = new StringBuilder(source);
		while (builder.length() < needed)
			builder.append(ch);
		return builder.toString();
	}
	private void selectRow()
	{
		while (selectedRow == -1)
		{
			str("Enter index:");
			String sIndex = readString();
			if (isInt(sIndex))
			{
				int index = Integer.parseInt(sIndex);
				if (index > -1)
					if (index < tableProvider.getRowCount())
						selectedRow = index;
			}
			if (selectedRow == -1)
				str("Invalid row index.");
		}
	}
	@Override
	public void run()
	{
		if (!"".equals(caption))
			str(caption);
		if (tableProvider == null)
			return;
		if (tableProvider.getColCount() < 1)
			return;
		// evaluating data
		int[] colWidths = new int[tableProvider.getColCount()];
		int totalLineWidth = 0;
		int lineIdxColWidth = 0;
		int screenLineSize = 80;
		if (isSelectable())
		{
			lineIdxColWidth = String.valueOf(tableProvider.getRowCount() - 1).length();
			totalLineWidth += 1 + lineIdxColWidth;
		}
		for (int col = 0; col < tableProvider.getColCount(); col++)
		{
			int maxColWidth = getColumnWidth(col);
			colWidths[col] = maxColWidth;
			totalLineWidth += maxColWidth + 1;
		}
		// header
		str("");
		StringBuilder data = new StringBuilder();
		if (isSelectable())
		{
			data.append(padding("", lineIdxColWidth, ' '));
			data.append('|');
		}
		for (int col = 0; col < tableProvider.getColCount(); col++)
		{
			if (col > 0)
				data.append('|');
			data.append(padding(tableProvider.getColName(col), colWidths[col], ' '));
		}
		str(data.toString());
		str(padding("", Math.min(totalLineWidth, screenLineSize), '-'));
		// rows
		for (int row = 0; row < tableProvider.getRowCount(); row++)
		{
			data = new StringBuilder();
			if (isSelectable())
			{
				data.append(padding(String.valueOf(row), lineIdxColWidth, ' '));
				data.append('|');
			}
			for (int col = 0; col < tableProvider.getColCount(); col++)
			{
				if (col > 0)
					data.append('|');
				data.append(padding(tableProvider.getCellValue(row, col), colWidths[col], ' '));
			}
			str(data.toString());
		}
		// index
		str("");
		if (tableProvider.getRowCount() > 0)
			if (isSelectable())
			{
				selectRow();
				return;
			}
		if(isWaitable())
			waitForInput();
	}

}
