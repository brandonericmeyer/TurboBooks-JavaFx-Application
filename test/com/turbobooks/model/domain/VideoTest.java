package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

public class VideoTest extends TestCase {

	static final Catalog CATALOG = new Catalog("catalog");
	static final byte RATING = 1;
	static final String TITLE = "title";
	static final String UUID = "123";
	static final String MEMBERNUMBER = "45t6";
	static final boolean STATUS = true;

	/**
	 * Tests if two objects are equal
	 */
	@Test
	public void testEquals() {

		Video m1 = new Video(CATALOG, RATING, TITLE, UUID, MEMBERNUMBER, STATUS);
		Video m2 = new Video(CATALOG, RATING, TITLE, UUID, MEMBERNUMBER, STATUS);
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
		Video m1 = new Video(CATALOG, RATING, "TopGun", "2321", MEMBERNUMBER, false);
		Video m2 = new Video(CATALOG, RATING, TITLE, UUID, MEMBERNUMBER, STATUS);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEquals PASSED");
	}

}
