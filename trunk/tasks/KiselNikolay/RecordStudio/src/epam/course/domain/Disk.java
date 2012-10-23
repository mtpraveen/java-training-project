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

}
