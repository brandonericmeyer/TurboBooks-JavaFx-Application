package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author brandonmeyer
 *
 */
public class AdminTest extends TestCase {

	static final String FIRSTNAME = "John";
	static final String LASTNAME = "Smith";
	static final String EMPLOYEENUMBER = "123A";

	/**
	 * Tests with an valid Admin passed in
	 */
	@Test
	public void testValidate() {
		Admin m1 = new Admin(FIRSTNAME, LASTNAME, EMPLOYEENUMBER);
		// m1.validate should assert to True since all variables
		// being passed to create a new Admin are all valid.
		assertTrue("m1 validates", m1.validate());
		System.out.println("testValidate PASSED");
	}

	/**
	 * Tests with an invalid Admin passed in
	 */
	@Test
	public void testNotValidate() {
		Admin m1 = new Admin(FIRSTNAME, null, null);
		// m1.validate should not pass here since we are not
		// sending in valid parameters - in the case of Admin
		// class, its valid only if all class variables are passed
		assertFalse("m1 does not validate", m1.validate());
		System.out.println("testNotValidate PASSED");
	}

	/**
	 * Tests if two admins are equal
	 */
	@Test
	public void testEqualsAdmin() {
		Admin m1 = new Admin(FIRSTNAME, LASTNAME, EMPLOYEENUMBER);
		Admin m2 = new Admin(FIRSTNAME, LASTNAME, EMPLOYEENUMBER);
		// this should assert to true since the contents of
		// m1 and m2 class variables are identical.
		assertTrue("m1 equals m2", m1.equals(m2));
		System.out.println("testEqualsAdmin PASSED");
	}

	/**
	 * Tests if two Members are not equal
	 */
	@Test
	public void testNotEqualsAdmin() {
		Admin m1 = new Admin(FIRSTNAME, LASTNAME, EMPLOYEENUMBER);
		Admin m2 = new Admin("Mary", LASTNAME, EMPLOYEENUMBER);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEqualsAdmin PASSED");
	}
}
