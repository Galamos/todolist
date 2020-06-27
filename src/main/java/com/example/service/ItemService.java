package com.example.service;

import com.example.model.Item;

import java.util.List;

public interface ItemService {

     public Item isItemExist(Item item);
     public Item saveOrUpdateItem(Item item);
     public List<Item> getItems();
     public Item getItem(int id);
     public void deleteItem(int id);
}
