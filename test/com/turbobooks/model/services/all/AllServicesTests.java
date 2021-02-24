package com.turbobooks.model.services.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.turbobooks.model.services.checkoutservice.CheckoutServiceImplTest;
import com.turbobooks.model.services.factory.ServiceFactoryTest;
import com.turbobooks.model.services.itemservice.ItemManagerServiceImplTest;

/**
 * Runs a test suite on services related features
 * 
 * @author brandonmeyer
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ServiceFactoryTest.class, CheckoutServiceImplTest.class, ItemManagerServiceImplTest.class })
public class AllServicesTests {

}
