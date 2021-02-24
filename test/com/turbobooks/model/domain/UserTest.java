package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

public class UserTest extends TestCase {

	static final String FIRSTNAME = "John";
	static final String LASTNAME = "Smith";
	static final String USERNUMBER = "123a";

	/**
	 * Tests with an valid Admin passed in
	 */
	@Test
	public void testValidateAdmin() {
		User m1 = new Admin(FIRSTNAME, LASTNAME, USERNUMBER);
		// m1.validate should assert to True since all variables
		// being passed to create a new Admin are all valid.
		assertTrue("m1 validates", ((Admin) m1).validate());
		System.out.println("testValidateAdmin PASSED");
	}

	/**
	 * Tests with an valid Member passed in
	 */
	@Test
	public void testValidateMember() {
		User m1 = new Member(FIRSTNAME, LASTNAME, USERNUMBER);
		// m1.validate should assert to True since all variables
		// being passed to create a new Member are all valid.
		assertTrue("m1 validates", ((Member) m1).validate());
		System.out.println("testValidateMember PASSED");
	}

	/**
	 * Tests with an invalid Admin passed in
	 */
	@Test
	public void testNotValidateAdmin() {
		User m1 = new Admin(FIRSTNAME, null, null);
		// m1.validate should not pass here since we are not
		// sending in valid parameters - in the case of Admin
		// class, its valid only if all class variables are passed
		assertFalse("m1 does not validate", ((Admin) m1).validate());
		System.out.println("testNotValidateAdmin PASSED");
	}

	/**
	 * Tests with an invalid Member passed in
	 */
	@Test
	public void testNotValidateMember() {
		User m1 = new Member(FIRSTNAME, null, null);
		// m1.validate should not pass here since we are not
		// sending in valid parameters - in the case of Member
		// class, its valid only if all class variables are passed
		assertFalse("m1 does not validate", ((Member) m1).validate());
		System.out.println("testNotValidateMember PASSED");
	}

	/**
	 * Tests if two Admins are equal
	 */
	@Test
	public void testEqualsUserAdmin() {
		User m1 = new Admin(FIRSTNAME, LASTNAME, USERNUMBER);
		User m2 = new Admin(FIRSTNAME, LASTNAME, USERNUMBER);
		// this should assert to true since the contents of
		// m1 and m2 class variables are identical.
		assertTrue("m1 equals m2", m1.equals(m2));
		System.out.println("testEqualsUserAdmin PASSED");
	}

	/**
	 * Tests if two Members are equal
	 */
	@Test
	public void testEqualsUserMember() {
		User m1 = new Member(FIRSTNAME, LASTNAME, USERNUMBER);
		User m2 = new Member(FIRSTNAME, LASTNAME, USERNUMBER);
		// this should assert to true since the contents of
		// m1 and m2 class variables are identical.
		assertTrue("m1 equals m2", m1.equals(m2));
		System.out.println("testEqualsUserMember PASSED");
	}

	/**
	 * Tests if two Members are not equal
	 */
	@Test
	public void testNotEqualsUserMember() {
		User m1 = new Member(FIRSTNAME, LASTNAME, USERNUMBER);
		User m2 = new Member("Mary", LASTNAME, USERNUMBER);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEqualsUserMember PASSED");
	}

	/**
	 * Tests if two Admins are not equal
	 */
	@Test
	public void testNotEqualsUserAdmin() {
		User m1 = new Admin(FIRSTNAME, LASTNAME, USERNUMBER);
		User m2 = new Admin("Mary", LASTNAME, USERNUMBER);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEqualsUserAdmin PASSED");
	}
}
