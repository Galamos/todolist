package com.example.controller;

import com.example.model.Item;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired(required = true)
    @Qualifier(value = "itemService")
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<?> createItem(@RequestBody Item item){
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
            return new ResponseEntity(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        itemService.deleteItem(id);
        return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
    }


}
