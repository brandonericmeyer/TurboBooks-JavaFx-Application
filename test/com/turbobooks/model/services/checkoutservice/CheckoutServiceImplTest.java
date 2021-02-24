package com.turbobooks.model.services.checkoutservice;

import com.turbobooks.model.business.exception.ServiceLoadException;
import com.turbobooks.model.domain.Book;
import com.turbobooks.model.domain.Catalog;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.domain.Member;
import com.turbobooks.model.domain.User;
import com.turbobooks.model.services.exception.CheckoutException;
import com.turbobooks.model.services.exception.ItemException;
import com.turbobooks.model.services.factory.ServiceFactory;
import com.turbobooks.model.services.manager.PropertyManager;

import junit.framework.TestCase;

/**
 * Tests checkoutservice related features
 * 
 * @author brandonmeyer
 *
 */
public class CheckoutServiceImplTest extends TestCase {

	static final String DUMMYSTR = "Smith";
	static final String FIRSTNAME = "John";
	static final String LASTNAME = "Smith";
	static final String MEMBERNUMBER = "123D";
	private ServiceFactory serviceFactory;
	private Item item;
	private User user;

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		String propertyFileLocation = System.getProperty("prop_location");

		PropertyManager.loadProperties(propertyFileLocation);

		serviceFactory = ServiceFactory.getInstance();
		item = new Book(new Catalog(DUMMYSTR), DUMMYSTR, DUMMYSTR, DUMMYSTR, DUMMYSTR, DUMMYSTR, DUMMYSTR, true);
		user = new Member(FIRSTNAME, LASTNAME, MEMBERNUMBER);
	}

	/**
	 * Testing checkoutItemService
	 */
	public final void testCheckoutItem() {
		try {
			final ICheckoutService checkoutService = (ICheckoutService) serviceFactory
					.getService(ICheckoutService.NAME);
			assertFalse(checkoutService.checkoutItem(MEMBERNUMBER, item.getUuid()));
			System.out.println("testCheckoutItem PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (CheckoutException e) {
			e.printStackTrace();
			fail("CheckoutException");
		} catch (ItemException e) {
			e.printStackTrace();
			fail("CheckoutException");
		}
	}

	/**
	 * Testing isItemAvailable
	 */
	public final void testIsItemAvailable() {

		try {
			final ICheckoutService checkoutService = (ICheckoutService) serviceFactory
					.getService(ICheckoutService.NAME);
			assertFalse(checkoutService.isItemAvailable(item.getUuid()));
			System.out.println("testIsItemAvailable PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (CheckoutException e) {
			e.printStackTrace();
			fail("CheckoutException");
		}
	}
}
