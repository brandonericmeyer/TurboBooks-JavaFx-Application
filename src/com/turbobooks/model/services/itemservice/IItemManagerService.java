package com.turbobooks.model.services.itemservice;

import com.turbobooks.model.domain.Item;
import com.turbobooks.model.services.IService;
import com.turbobooks.model.services.exception.ItemException;

/**
 * Interface for managing item related services
 * 
 * @author brandonmeyer
 *
 */
public interface IItemManagerService extends IService {

	public final String NAME = "IItemManagerService";

	public boolean addItem(Item item) throws ItemException;

	public boolean removeItem(Item item) throws ItemException;

	public Item getItem(String uuid) throws ItemException;

	public boolean updateItem(Item item) throws ItemException;

}
