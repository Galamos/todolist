package com.example.controller;

import com.example.model.Item;
import com.example.service.ItemService;
import com.example.util.CustomErrorLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@RequestBody Item item){
        if(itemService.isItemExist(item)){
            return new ResponseEntity(new CustomErrorLogger("Cannot add item because item with id: " + item.getId() + " already exist"), HttpStatus.CONFLICT);
        }
        itemService.saveOrUpdateItem(item);
        return new ResponseEntity<String>("Task has been saved!", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> getItems(){
        List<Item> items = itemService.getItems();
        if(items.isEmpty()){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> editItem(@PathVariable("id") int id, @RequestBody Item item){

        Item currentItem = itemService.getItem(id);
        if (currentItem == null){
            return new ResponseEntity(new CustomErrorLogger("Item with id: " + id + "was not found!"), HttpStatus.NOT_FOUND);
        }
        currentItem.setDescription(item.getDescription());
        currentItem.setStatus(item.getStatus());
        itemService.saveOrUpdateItem(currentItem);
        return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItem(@PathVariable("id") int id){
        Item item = itemService.getItem(id);
        if (item == null){
            return new ResponseEntity(new CustomErrorLogger("Item with id: " + id + "not found!"), HttpStatus.NOT_FOUND);
        }
        itemService.deleteItem(id);
        return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
    }


}
