package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

public class DiscTest extends TestCase {

	static final Catalog CATALOG = new Catalog("catalog");
	static final String ISBNNUMBER = "123";
	static final String ARTISTFIRSTNAME = "John";
	static final String ARTISTLASTNAME = "Smith";
	static final String TITLE = "title";
	static final String ID = "42";
	static final String MEMBERNUMBER = "45t6";
	static final boolean STATUS = true;

	/**
	 * Tests if two objects are equal
	 */
	@Test
	public void testEquals() {

		Disc m1 = new Disc(CATALOG, ISBNNUMBER, ARTISTFIRSTNAME, ARTISTLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
		Disc m2 = new Disc(CATALOG, ISBNNUMBER, ARTISTFIRSTNAME, ARTISTLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
		// this should assert to true since the contents of
		// m1 and m2 class variables are identical.
		assertTrue("m1 equals m2", m1.equals(m2));
		System.out.println("testEquals PASSED");
	}

	/**
	 * Tests if two objects are not equal
	 */
	@Test
	public void testNotEquals() {
		Disc m1 = new Disc(CATALOG, ISBNNUMBER, ARTISTFIRSTNAME, ARTISTLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
		Disc m2 = new Disc(CATALOG, null, ARTISTFIRSTNAME, "Clinton", TITLE, ID, MEMBERNUMBER, STATUS);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEquals PASSED");
	}

}
