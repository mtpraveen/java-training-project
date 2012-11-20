package motor.depot.storages.csv;

import java.util.ArrayList;

import motor.depot.storages.csv.utils.CsvBuilder;

import org.junit.Test;
import static org.junit.Assert.*;


public class CsvBuilderTest {
	@Test
	public void test1()
	{
		ArrayList<String> array = new ArrayList<String>();
		String data = (new CsvBuilder()).create(array);
		assertEquals("", data);
	}
	@Test
	public void test2()
	{
		ArrayList<String> array = new ArrayList<String>();
		array.add("1");
		String data = (new CsvBuilder()).create(array);
		assertEquals("1", data);
	}
	@Test
	public void test3()
	{
		ArrayList<String> array = new ArrayList<String>();
		array.add("1");
		array.add("");
		array.add("3");
		String data = (new CsvBuilder()).create(array);
		assertEquals("1;;3", data);
	}
	@Test
	public void test4()
	{
		ArrayList<String> array = new ArrayList<String>();
		array.add("1");
		array.add("");
		array.add("3;4");
		String data = (new CsvBuilder()).create(array);
		assertEquals("1;;\"3;4\"", data);
	}
	@Test
	public void test5()
	{
		ArrayList<String> array = new ArrayList<String>();
		array.add("11\" \"");
		array.add("");
		array.add("3;4");
		array.add("");
		String data = (new CsvBuilder()).create(array);
		assertEquals("\"11\"\" \"\"\";;\"3;4\";", data);
	}

}
