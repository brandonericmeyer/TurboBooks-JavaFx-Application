package com.turbobooks.model.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author brandonmeyer
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AdminTest.class, BookTest.class, CatalogTest.class, MemberTest.class, VideoTest.class})
public class AllDomainTests {

}