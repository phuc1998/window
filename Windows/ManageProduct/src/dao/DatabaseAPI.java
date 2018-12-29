/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.corba.se.spi.activation.Repository;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import entities.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;

/**
 *
 * @author PHUC
 */
public class DatabaseAPI {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    public static List<Inventory> getListRepository() {
        List<Inventory> list = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            return SESSION_FACTORY.getCurrentSession().createCriteria(Inventory.class).list();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Category> getListCategory() {
        List<Category> list = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            return SESSION_FACTORY.getCurrentSession().createCriteria(Category.class).list();
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Inventory> searchRepository(String search) {
        try {
//            String hql = "FROM " + Phone.class.getName() + " p WHERE p.name LIKE ?1";
//            Session session = SESSION_FACTORY.getCurrentSession();
//            session.beginTransaction();
//            Query query = session.createQuery(hql);
//            query.setParameter(1, "%"+search+"%");
//            List<Phone> results = query.list();
//            session.getTransaction().commit();
//            return results;
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public static boolean saveOrUpdateRepository(Inventory inventory) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.saveOrUpdate(inventory);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean saveOrUpdatePhone(Phone phone) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.saveOrUpdate(phone);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Phone> getListPhone() {
        List<Phone> phones = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Phone.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            phones = criteria.list();
            return phones;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Discount> getListDiscounts(){
        List<Discount> discounts = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Discount.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            discounts = criteria.list();
            return discounts;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static List<Payment> getListPayments(){
        List<Payment> payments = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Payment.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            payments = criteria.list();
            return payments;
        } catch (Exception e) {
            return null;
        }
    }
}
