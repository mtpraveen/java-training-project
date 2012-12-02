/**
 * 
 */
package motor.depot.listclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import motor.depot.clientserver.server.scenario.tables.ITableProvider;
import motor.depot.storages.csv.utils.CsvSplitter;
import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ILoadableFromCsv;

/**
 * @author dima
 * 
 */
public class ListWithIds<T extends ICanBeSaved> extends ArrayList<T>
{
	private T prototype;

	/**
	 * @param prototype
	 */
	public ListWithIds(T prototype) {
		super();
		this.prototype = prototype;
	}

	/*
	 * public T findById(int id) { for (T withId : this) { if (withId.getId() ==
	 * id) return withId; } return null; }
	 */
	/*
	 * public void save(AbstractStorage storage) { for (T canBeSaved : this) {
	 * AbstractItemStateSaver saver = canBeSaved.getSaver(storage);
	 * storage.addSaver(saver); } }
	 */
	public ITableProvider getTableProvider()
	{
		return new ITableProvider() {

			@Override
			public int getRowCount()
			{
				return size();
			}

			@Override
			public String getColName(int col)
			{
				return prototype.getRowProvider().getColName(col);
			}

			@Override
			public int getColCount()
			{
				return prototype.getRowProvider().getColCount();
			}

			@Override
			public String getCellValue(int row, int col)
			{
				return get(row).getRowProvider().getValue(col);
			}
		};
	}

	public ListWithIds<T> getCopy()
	{
		ListWithIds<T> copy = new ListWithIds<T>(prototype);
		for (T iCanBeSaved : this)
		{
			copy.add(iCanBeSaved);
		}
		return copy;
	}

	public void saveToStream(ObjectOutputStream stream) throws IOException
	{
		for (T element : this)
		{
			stream.writeObject(element);
		}
	}

	public boolean addObjectIfMatchType(Object object)
	{
		if (object.getClass().equals(prototype.getClass()))
		{
			add((T) object);
			return true;
		}
		return false;
	}

	/**
	 * @return
	 */
	public T getPrototype()
	{
		return prototype;
	}

	private static class NonEmptyStringsReader
	{
		BufferedReader reader;

		public NonEmptyStringsReader(BufferedReader reader) {
			super();
			this.reader = reader;
		}

		String getNextString()
		{
			String res = null;
			try
			{
				while ((res = reader.readLine()) != null)
				{
					if (!res.equals(""))
						return res;
				}
			} catch (IOException e)
			{
			}
			return null;
		}

	}

	public int appendFromCsvStream(BufferedReader reader)
	{
		int res = 0;
		if (!(prototype instanceof ILoadableFromCsv))
			return 0;
		NonEmptyStringsReader nonEmptyStringsReader = new NonEmptyStringsReader(reader);
		String sCaptions = nonEmptyStringsReader.getNextString();
		if (sCaptions == null)
			return 0;
		ArrayList<String> captions = CsvSplitter.parse(sCaptions);
		if (captions.size() < prototype.getRowProvider().getColCount())
			return 0;
		for (int i = 0; i < prototype.getRowProvider().getColCount(); i++)
		{
			if (!captions.get(i).equalsIgnoreCase(prototype.getRowProvider().getColName(i)))
				return 0;
		}
		String row;
		while ((row = nonEmptyStringsReader.getNextString()) != null)
		{
			ArrayList<String> values = CsvSplitter.parse(row);
			if (values.size() >= prototype.getRowProvider().getColCount())
			{
				T newItem = (T) prototype.newInstance();
				for (int i = 0; i < prototype.getRowProvider().getColCount(); i++)
				{
					((ILoadableFromCsv) newItem).setField(i, values.get(i));
				}
				if (indexOf(newItem) == -1)
				{
					add(newItem);
					res++;
				}
			}
		}
		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((prototype == null) ? 0 : prototype.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	private boolean sameContent(ListWithIds<T> list)
	{
		if (list.size() == size())
		{
			for (int i = 0; i < size(); i++)
			{
				if ((get(i) == null) && (list.get(i) == null))
					continue;
				if (get(i) != null)
				{
					if (get(i).equals(list.get(i)))
					{
						continue;
					}
				}
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListWithIds other = (ListWithIds) obj;
		if (prototype == null)
		{
			if (other.prototype != null)
				return false;
		}
		else if (!prototype.equals(other.prototype))
			return false;
		return sameContent(other);
	}
}
