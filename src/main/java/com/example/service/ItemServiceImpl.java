package com.example.service;

import com.example.dao.ItemDao;
import com.example.model.Item;
import com.example.util.CustomErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService{


    @Autowired
    private ItemDao itemDao;

    @Override
    public boolean isItemExist(Item item) {
        return itemDao.findById(item.getId());
    }

    @Override
    public Item saveOrUpdateItem(Item item) {
        return itemDao.saveItem(item);
    }

    @Override
    public List<Item> getItems() {
        return (List<Item>) itemDao.getAll();
    }

    @Override
    public Item getItem(int id) {
        return itemDao.locateItem(id);
    }

    @Override
    public void deleteItem(int id) {
         itemDao.delete(id);
    }
}
