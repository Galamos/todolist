package com.example.dao;

import com.example.model.Item;
import com.example.util.CustomErrorLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.antlr.v4.codegen.DefaultOutputModelFactory.list;

@Repository("itemDao")
public class ItemDaoImpl implements ItemDao {

    private CustomErrorLogger customErrorLogger;

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

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
        List<Item> items = session.createQuery("").list();

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
