package com.turbobooks.model.business.factory;

import org.junit.Test;

import com.turbobooks.model.business.exception.ServiceLoadException;
import com.turbobooks.model.services.checkoutservice.CheckoutServiceImpl;
import com.turbobooks.model.services.checkoutservice.ICheckoutService;
import com.turbobooks.model.services.factory.ServiceFactory;
import com.turbobooks.model.services.itemservice.IItemManagerService;
import com.turbobooks.model.services.itemservice.ItemManagerServiceImpl;

import junit.framework.TestCase;

/**
 * @author brandonmeyer
 *
 */
public class ServiceFactoryTest extends TestCase {

	ServiceFactory serviceFactory;

	@Override
	public void setUp() throws Exception {
		serviceFactory = ServiceFactory.getInstance();
	}

	public void testGetInstance() {
		assertNotNull(serviceFactory);
	}

	/**
	 * Test Factory to return the IItemManagerService and assert it by checking it
	 * is an instance of ItemManagerServiceImpl
	 * 
	 * This should be true since ItemManagerServiceImpl implements
	 * IItemManagerService
	 */
	@Test
	public void testGetItemManagerService() {
		try {
			IItemManagerService itemService;
			itemService = (IItemManagerService) serviceFactory.getService(IItemManagerService.NAME);
			assertTrue(itemService instanceof ItemManagerServiceImpl);
			System.out.println("testCheckoutService PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		}
	}

	/**
	 * Test Factory to return the ICheckoutService and assert it by checking it is
	 * an instance of CheckoutServiceImpl
	 * 
	 * This should be true since CheckoutServiceImpl implements ICheckoutService
	 */
	@Test
	public void testGetCheckoutService() {
		try {
			ICheckoutService checkoutService;
			checkoutService = (ICheckoutService) serviceFactory.getService(ICheckoutService.NAME);
			assertTrue(checkoutService instanceof CheckoutServiceImpl);
			System.out.println("testCheckoutService PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		}
	}

}
