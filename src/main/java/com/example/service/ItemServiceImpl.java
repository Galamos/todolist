package com.example.service;

import com.example.dao.ItemDao;
import com.example.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{


    @Autowired
    private ItemDao itemDao;

    @Override
    @Transactional
    public Item isItemExist(Item item) {
        return itemDao.locateItem(item.getId());
    }

    @Override
    @Transactional
    public Item saveOrUpdateItem(Item item) {
        return itemDao.saveItem(item);
    }

    @Override
    @Transactional
    public List<Item> getItems() {
        return (List<Item>) itemDao.getAll();
    }

    @Override
    @Transactional
    public Item getItem(int id) {
        return itemDao.locateItem(id);
    }

    @Override
    @Transactional
    public void deleteItem(int id) {
         itemDao.delete(id);
    }
}
