package com.turbobooks.model.business.manager;

import com.turbobooks.model.domain.Book;
import com.turbobooks.model.domain.Catalog;
import com.turbobooks.model.domain.Item;
import com.turbobooks.model.domain.Member;
import com.turbobooks.model.services.itemservice.IItemManagerService;

import junit.framework.TestCase;

/**
 * @author brandonmeyer
 *
 */
public class TurbobooksManagerTest extends TestCase {

	static final Catalog CATALOG = new Catalog("catalog");
	static final String ISBNNUMBER = "123";
	static final String AUTHORFIRSTNAME = "John";
	static final String AUTHORLASTNAME = "Smith";
	static final String TITLE = "title";
	static final String ID = "42";
	static final String MEMBERNUMBER = "45t6";
	static final String FIRSTNAME = "John";
	static final String LASTNAME = "Smith";
	static final boolean STATUS = true;

	private TurbobooksManager turboManager;
	private Item item;
	private Member member;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		turboManager = TurbobooksManager.getInstance();

		item = new Book(CATALOG, ISBNNUMBER, AUTHORFIRSTNAME, AUTHORLASTNAME, TITLE, ID, MEMBERNUMBER, STATUS);
		member = new Member(FIRSTNAME, LASTNAME, MEMBERNUMBER);
	}

	/**
	 * Test performAction method for ADDITEM
	 */
	public final void testPerformActionAddItem() {
		boolean action = turboManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item,
				member.getMemberNumber());
		assertTrue(action);

		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());
	}

	/**
	 * Test performAction method for REMOVEITEM
	 */
	public final void testPerformActionRemoveItem() {
		// Add item to remove
		turboManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, member.getMemberNumber());

		boolean action = turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item,
				member.getMemberNumber());
		assertTrue(action);
	}

	/**
	 * Test performAction method for UPDATEITEM
	 */
	public final void testPerformActionUpdateItem() {
		boolean action = false;

		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());
		action = turboManager.performAction(TurbobooksManager.Services.UPDATEITEM.getLabel(), item,
				member.getMemberNumber());
		assertFalse(action);

		turboManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, member.getMemberNumber());
		action = turboManager.performAction(TurbobooksManager.Services.UPDATEITEM.getLabel(), item,
				member.getMemberNumber());
		assertTrue(action);

		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());
	}

	/**
	 * Test getItem method
	 */
	public final void testGetItem() {
		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());
		assertNull(turboManager.getItem(IItemManagerService.NAME, item.getUuid()));

		// Add item to retrieve
		turboManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, member.getMemberNumber());
		assertNotNull(turboManager.getItem(IItemManagerService.NAME, item.getUuid()));

		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());

	}

	/**
	 * Test performAction method for CHECKOUTITEM
	 */
	public final void testPerformActionCheckoutItem() {
		boolean action = turboManager.performAction(TurbobooksManager.Services.CHECKOUTITEM.getLabel(), item,
				member.getMemberNumber());
		assertFalse(action);

		// Add item to checkout
		turboManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, member.getMemberNumber());
		action = turboManager.performAction(TurbobooksManager.Services.CHECKOUTITEM.getLabel(), item,
				member.getMemberNumber());
		assertTrue(action);

		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());

	}

	/**
	 * Test performAction method for ISITEMAVAILABLE
	 */
	public final void testPerformActionIsItemAvailable() {
		turboManager.performAction(TurbobooksManager.Services.ADDITEM.getLabel(), item, member.getMemberNumber());

		boolean action = turboManager.performAction(TurbobooksManager.Services.ISITEMAVAILABLE.getLabel(), item,
				member.getMemberNumber());
		assertTrue(action);

		item.setAvailable(false);
		turboManager.performAction(TurbobooksManager.Services.UPDATEITEM.getLabel(), item, member.getMemberNumber());

		action = turboManager.performAction(TurbobooksManager.Services.ISITEMAVAILABLE.getLabel(), item,
				member.getMemberNumber());
		assertFalse(action);

		turboManager.performAction(TurbobooksManager.Services.REMOVEITEM.getLabel(), item, member.getMemberNumber());

	}

}
