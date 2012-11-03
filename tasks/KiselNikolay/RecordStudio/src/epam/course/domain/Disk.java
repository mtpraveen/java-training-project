package epam.course.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Disk {

	/**
	 * @param args
	 */
	private String name;
	private List<Record> records;

	public Disk(String name) {
		this.name = name;
		records = new ArrayList<Record>();
	}

	public Disk(String name, Record[] records) {
		this.name = name;
		this.records = Arrays.asList(records);
	}

	public void addRecord(Record rec) {
		records.add(rec);
	}

	public void deleteRecord(Record rec) {
		records.remove(rec);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((records == null) ? 0 : records.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disk other = (Disk) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (records == null) {
			if (other.records != null)
				return false;
		} else if (!records.equals(other.records))
			return false;
		return true;
	}

}
