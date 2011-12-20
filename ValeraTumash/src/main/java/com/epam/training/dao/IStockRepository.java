package com.epam.training.dao;

import java.util.Collection;
import java.util.Date;

import com.epam.training.model.Item;
import com.epam.training.model.ModifiedItem;
import com.epam.training.model.User;

public interface IStockRepository {
	void createItem(String title, int quantity);
	
	Item findItemById(long id);
	
	Collection<Item> findAllItems();
	
	ModifiedItem modifyItem(long id, Date d, int q, User u);
	
	

}
