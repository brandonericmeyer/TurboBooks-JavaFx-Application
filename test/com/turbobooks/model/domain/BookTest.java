package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

public class BookTest extends TestCase {

	static final Catalog CATALOG = new Catalog("catalog");
	static final String ISBNNUMBER = "123";
	static final String AUTHORFIRSTNAME = "John";
	static final String AUTHORLASTNAME = "Smith";
	static final String TITLE = "title";
	static final String ID = "42";
	static final String MEMBERNUMBER = "45t6";
	static final boolean STATUS = true;

	/**
	 * Tests if two objects are equal
	 */
	@Test
	public void testEquals() {

		Book m1 = new Book(CATALOG, ISBNNUMBER, AUTHORFIRSTNAME, AUTHORLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
		Book m2 = new Book(CATALOG, ISBNNUMBER, AUTHORFIRSTNAME, AUTHORLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
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
		Book m1 = new Book(CATALOG, ISBNNUMBER, AUTHORFIRSTNAME, AUTHORLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
		Book m2 = new Book(CATALOG, ISBNNUMBER, "Marry", AUTHORLASTNAME, TITLE, ID, MEMBERNUMBER, false);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEquals PASSED");
	}

}
