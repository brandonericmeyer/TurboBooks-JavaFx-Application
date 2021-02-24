package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

public class CatalogTest extends TestCase {
	static final String NAME = "John Smith";

	/**
	 * Tests with an valid Member passed in
	 */
	@Test
	public void testValidate() {
		Catalog m1 = new Catalog(NAME);
		// m1.validate should assert to True since all variables
		// being passed to create a new Catalog are all valid.
		assertTrue("m1 validates", m1.validate());
		System.out.println("testValidate PASSED");
	}

	/**
	 * Tests if two objects are equal
	 */
	@Test
	public void testEquals() {
		Catalog m1 = new Catalog(NAME);
		Catalog m2 = new Catalog(NAME);
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
		Catalog m1 = new Catalog(NAME);
		Catalog m2 = new Catalog("Marry");
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEquals PASSED");
	}

}
