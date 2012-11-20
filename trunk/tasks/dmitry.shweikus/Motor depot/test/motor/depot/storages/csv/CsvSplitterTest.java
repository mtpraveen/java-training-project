package motor.depot.storages.csv;

import java.util.ArrayList;

import junit.framework.Assert;
import static junit.framework.Assert.*;

import motor.depot.storages.csv.utils.CsvSplitter;

import org.junit.Test;

public class CsvSplitterTest {
	@Test
	public void test0() {
		ArrayList<String> a = CsvSplitter.parse("");
		assertEquals(a.size(), 1);
		Assert.assertEquals(a.get(0), "");
	}
	@Test
	public void test0a() {
		ArrayList<String> a = CsvSplitter.parse(";");
		assertEquals(a.size(), 2);
		Assert.assertEquals(a.get(0), "");
		Assert.assertEquals(a.get(1), "");
	}
	@Test
	public void test1() {
		ArrayList<String> a = CsvSplitter.parse("a;b");
		assertEquals(a.size(), 2);
		Assert.assertEquals(a.get(0), "a");
		Assert.assertEquals(a.get(1), "b");
	}
	@Test
	public void test1a() {
		ArrayList<String> a = CsvSplitter.parse("a");
		assertEquals(a.size(), 1);
		Assert.assertEquals(a.get(0), "a");
	}

	@Test
	public void test2() {
		ArrayList<String> a = CsvSplitter.parse("a;b;c;d");
		assertEquals(a.size(), 4);
		Assert.assertEquals(a.get(0), "a");
		Assert.assertEquals(a.get(1), "b");
		Assert.assertEquals(a.get(2), "c");
		Assert.assertEquals(a.get(3), "d");
	}

	@Test
	public void test3() {
		ArrayList<String> a = CsvSplitter.parse("a;b;\"ccc\";d");
		assertEquals(a.size(), 4);
		Assert.assertEquals(a.get(0), "a");
		Assert.assertEquals(a.get(1), "b");
		Assert.assertEquals(a.get(2), "ccc");
		Assert.assertEquals(a.get(3), "d");
	}

	@Test
	public void test4() {
		ArrayList<String> a = CsvSplitter.parse("\"a;aa\";b;\"ccc\";d");
		assertEquals(a.size(), 4);
		Assert.assertEquals(a.get(0), "a;aa");
		Assert.assertEquals(a.get(1), "b");
		Assert.assertEquals(a.get(2), "ccc");
		Assert.assertEquals(a.get(3), "d");
	}

	@Test
	public void test5() {
		ArrayList<String> a = CsvSplitter.parse("\"a;a\"\";a;\";b;\"cc;c;\";d");
		Assert.assertEquals("a;a\";a;", a.get(0));
		Assert.assertEquals("b", a.get(1));
		Assert.assertEquals("cc;c;", a.get(2));
		Assert.assertEquals("d", a.get(3));
		assertEquals(a.size(), 4);
	}

	@Test
	public void test6() {
		ArrayList<String> a = CsvSplitter.parse("\"a;a\"\";a;\";b;\"cc;c;\";;\"d;d\"");
		Assert.assertEquals("a;a\";a;", a.get(0));
		Assert.assertEquals("b", a.get(1));
		Assert.assertEquals("cc;c;", a.get(2));
		Assert.assertEquals("", a.get(3));
		Assert.assertEquals("d;d", a.get(4));
		assertEquals(a.size(), 5);
	}
}
