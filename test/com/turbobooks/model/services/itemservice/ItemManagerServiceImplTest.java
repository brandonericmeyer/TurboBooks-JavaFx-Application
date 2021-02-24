package com.turbobooks.model.services.itemservice;

import com.turbobooks.model.business.exception.ServiceLoadException;
import com.turbobooks.model.domain.Book;
import com.turbobooks.model.domain.Catalog;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.exception.ItemException;
import com.turbobooks.model.services.factory.ServiceFactory;
import com.turbobooks.model.services.manager.PropertyManager;

import junit.framework.TestCase;

/**
 * Tests item management service
 * 
 * @author brandonmeyer
 *
 */
public class ItemManagerServiceImplTest extends TestCase {

	static final String DUMMYSTR = "Smith";
	private ServiceFactory serviceFactory;
	private Item item;

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
	}

	/**
	 * Testing addItem
	 */
	public final void testAddItem() {

		try {
			final IItemManagerService itemService = (IItemManagerService) serviceFactory
					.getService(IItemManagerService.NAME);
			assertTrue(itemService.addItem(item));
			itemService.removeItem(item); // clean up
			System.out.println("testAddItem PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (ItemException e) {
			e.printStackTrace();
			fail("ItemException");
		}
	}

	/**
	 * Testing removeItem
	 */
	public final void testRemoveItem() {
		try {
			final IItemManagerService itemService = (IItemManagerService) serviceFactory
					.getService(IItemManagerService.NAME);
			itemService.addItem(item);
			assertTrue(itemService.removeItem(item));
			System.out.println("testRemoveItem PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (ItemException e) {
			e.printStackTrace();
			fail("ItemException");
		}
	}

	/**
	 * Testing getItem returns a non null item from stored file
	 */
	public final void testGetItem() {
		try {
			final IItemManagerService itemService = (IItemManagerService) serviceFactory
					.getService(IItemManagerService.NAME);
			itemService.addItem(item);
			assertNotNull(itemService.getItem(item.getUuid()));
			itemService.removeItem(item); // clean up
			System.out.println("testGetItem PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (ItemException e) {
			e.printStackTrace();
			fail("ItemException");
		}
	}

	/**
	 * Testing getItem Should return true after updating book title on a stored item
	 * with same id
	 */
	public final void testUpdateItem() {
		try {
			final IItemManagerService itemService = (IItemManagerService) serviceFactory
					.getService(IItemManagerService.NAME);
			final String UPDATETITLE = "Jones";
			Item dummyUpdateItem = new Book(new Catalog(DUMMYSTR), DUMMYSTR, DUMMYSTR, DUMMYSTR, UPDATETITLE, DUMMYSTR,
					DUMMYSTR, true);
			itemService.addItem(dummyUpdateItem);

			assertTrue(itemService.updateItem(dummyUpdateItem));
			itemService.removeItem(dummyUpdateItem); // clean up
			System.out.println("testUpdateItem PASSED");
		} catch (ServiceLoadException e) {
			e.printStackTrace();
			fail("ServiceLoadException");
		} catch (ItemException e) {
			e.printStackTrace();
			fail("ItemException");
		}
	}
}
