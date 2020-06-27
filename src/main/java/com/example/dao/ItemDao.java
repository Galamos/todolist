package com.example.dao;

import com.example.model.Item;

import java.util.List;

public interface ItemDao {
    public Item saveItem(Item item);

    public Item locateItem(int id);

    public List<Item> getAll();

    public void delete(int id);
}
