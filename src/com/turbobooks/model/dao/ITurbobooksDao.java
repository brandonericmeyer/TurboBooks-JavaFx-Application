package com.turbobooks.model.dao;

import java.util.concurrent.ConcurrentHashMap;

import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.exception.ItemException;

/**
 * Calls the actual implementation of the service to access database
 * 
 * @author brandonmeyer
 *
 */
public interface ITurbobooksDao {
	public ConcurrentHashMap<String, Item> retrieveItems() throws ItemException;

	public boolean storeItems(final ConcurrentHashMap<String, Item> itemMap) throws ItemException;

	public boolean addItem(final Item item) throws ItemException;

	public boolean removeItem(final Item item) throws ItemException;

	public Item getItem(final String uuid) throws ItemException;

	public boolean updateItem(final Item item) throws ItemException;

	boolean removeItemFromStorage(String uuid) throws ItemException;

}