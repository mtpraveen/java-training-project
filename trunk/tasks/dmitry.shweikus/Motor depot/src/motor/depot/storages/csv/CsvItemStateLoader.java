package motor.depot.storages.csv;

import java.util.ArrayList;

import motor.depot.storages.interfaces.AbstractItemStateLoader;

public class CsvItemStateLoader extends AbstractItemStateLoader {
	ArrayList<String> values = new ArrayList<String>();
	@Override
	public String getClassId() {
		return values.get(0);
	}

	@Override
	public int getValuesCount() {
		return values.size()-1;
	}

	@Override
	public String getValue(int index) {
		if(index < 0)
			return "";
		else if(index < getValuesCount())
			return values.get(index+1);
		else
			return "";
	}
	
	public CsvItemStateLoader(ArrayList<String> parsedData) {
		values.addAll(parsedData);
	}

}
