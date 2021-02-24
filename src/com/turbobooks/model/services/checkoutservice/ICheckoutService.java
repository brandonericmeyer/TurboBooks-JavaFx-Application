package com.turbobooks.model.services.checkoutservice;

import com.turbobooks.model.services.IService;
import com.turbobooks.model.services.exception.CheckoutException;
import com.turbobooks.model.services.exception.ItemException;

/**
 * Services relating to checking an item out
 * 
 * @author brandonmeyer
 *
 */
public interface ICheckoutService extends IService {

	public final String NAME = "ICheckoutService";

	public boolean checkoutItem(final String memberId, final String uuid) throws CheckoutException, ItemException;

	public boolean isItemAvailable(final String uuid) throws CheckoutException;

}
