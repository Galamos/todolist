package com.example.dao;

import com.example.model.Item;
import com.example.util.CustomErrorLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemDaoImpl implements ItemDao {

    private CustomErrorLogger customErrorLogger;
    private SessionFactory sessionFactory;
    @Override
    public Item saveItem(Item item) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(item);
        session.flush();
        return item;
    }

    @Override
    public Item locateItem(int id) {
         Session session = this.sessionFactory.getCurrentSession();
         Item item = (Item) session.load(Item.class, id);
         return item;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Item> items = session.createQuery("From Item").list();

        for(Item item: items){
            customErrorLogger = new CustomErrorLogger("Item list: " + item);
        }
        return items;
    }


    @Override
    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Item item = (Item) session.load(Item.class, id);

        if (item != null)
            session.delete(item);
    }
}
