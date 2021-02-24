package com.turbobooks.model.domain;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author brandonmeyer
 *
 */
public class MemberTest extends TestCase {

	static final String FIRSTNAME = "John";
	static final String LASTNAME = "Smith";
	static final String MEMBERNUMBER = "123D";

	/**
	 * Tests with an valid Member passed in
	 */
	@Test
	public void testValidate() {
		Member m1 = new Member(FIRSTNAME, LASTNAME, MEMBERNUMBER);
		// m1.validate should assert to True since all variables
		// being passed to create a new Member are all valid.
		assertTrue("m1 validates", m1.validate());
		System.out.println("testValidate PASSED");
	}

	/**
	 * Tests with an invalid Member passed in
	 */
	@Test
	public void testNotValidate() {
		Member m1 = new Member(FIRSTNAME, null, null);
		// m1.validate should not pass here since we are not
		// sending in valid parameters - in the case of Member
		// class, its valid only if all class variables are passed
		assertFalse("m1 does not validate", m1.validate());
		System.out.println("testNotValidate PASSED");
	}

	/**
	 * Tests if two members are equal
	 */
	@Test
	public void testEqualsMember() {
		Member m1 = new Member(FIRSTNAME, LASTNAME, MEMBERNUMBER);
		Member m2 = new Member(FIRSTNAME, LASTNAME, MEMBERNUMBER);
		// this should assert to true since the contents of
		// m1 and m2 class variables are identical.
		assertTrue("m1 equals m2", m1.equals(m2));
		System.out.println("testEqualsMember PASSED");
	}

	/**
	 * Tests if two Members are not equal
	 */
	@Test
	public void testNotEqualsMember() {
		Member m1 = new Member(FIRSTNAME, LASTNAME, MEMBERNUMBER);
		Member m2 = new Member("Mary", LASTNAME, MEMBERNUMBER);
		// this should assert to false since the contents of
		// m1 and m2 class variables are NOT identical.
		assertFalse("m1 does NOT equal m2", m1.equals(m2));
		System.out.println("testNotEqualsMember PASSED");
	}
}
