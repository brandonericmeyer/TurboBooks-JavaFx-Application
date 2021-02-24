package com.turbobooks.model.business;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.turbobooks.model.business.manager.TurbobooksManagerTest;

/**
 * Properties are being passed is using the -D option.
 * 
 * @author brandonmeyer
 *
 */

@RunWith(Suite.class)
@SuiteClasses({ TurbobooksManagerTest.class })
public class AllBusinessTests {

}
