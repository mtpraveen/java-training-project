package com.epam.training.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.epam.training.model.Item;
import com.epam.training.model.ModifiedItem;
import com.epam.training.model.Role;
import com.epam.training.model.User;


public class SimpleStockRepository implements IStockRepository {
	private HashMap<Long, Item> items;

	public SimpleStockRepository() {
		init();
	}
	public void init(){
		User user = new User();
		user.setId(1L);
		user.setName("Petrov");
		user.setRole(Role.SALESMAN);
		
		items = new HashMap<Long, Item>();
		
		Item item = new Item();
		item.setId(1L);
		item.setTitle("Нож кухонный");
		item.setQuantity(10);
		items.put(1L, item);	
		
		item = new Item();
		item.setId(2L);
		item.setTitle("Ложка кухонная");
		item.setQuantity(3);
		items.put(2L, item);			
	}
	
	public void createItem(String title, int quantity) {
		// TODO Auto-generated method stub
	}

	public Item findItemById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public 	Collection<Item> findAllItems() {
		return items.values();
	}
	
	public ModifiedItem modifyItem(long id, Date d, int q, User u) {
		// TODO Auto-generated method stub
		return null;
	}

}
