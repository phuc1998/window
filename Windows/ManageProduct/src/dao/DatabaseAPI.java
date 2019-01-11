/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.sun.corba.se.spi.activation.Repository;
import com.sun.org.apache.xml.internal.resolver.Catalog;
import common.CommonFunction;
import entities.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.TemporalType;
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

    public static List<Inventory> getListRepository(int page, int max) {
        List<Inventory> inventorys = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Inventory.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(max);
            criteria.setFirstResult(page * max);
            inventorys = criteria.list();
            return inventorys;
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
        List<Inventory> list = null;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT DISTINCT I FROM " + Inventory.class.getName() + " I WHERE I.phone.name like '%" + search + "%'";
            Query query = session.createQuery(hql);
            list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveOrUpdateRepository(Inventory inventory) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.saveOrUpdate(inventory);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRepository(Inventory inventory) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.update(inventory);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Phone> filterPhone(Category category) {
        List<Phone> list = null;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT DISTINCT P FROM " + Phone.class.getName() + " P WHERE P.category.manufacturer = '" + category.getManufacturer() + "'";
            Query query = session.createQuery(hql);
            list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Phone> searchPhone(String search) {
        List<Phone> list = null;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT DISTINCT P FROM " + Phone.class.getName() + " P WHERE P.name like '%" + search + "%' or P.category.manufacturer like '%" + search + "%'";
            Query query = session.createQuery(hql);
            list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Bill> searchBill(String search) {
        List<Bill> list = null;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT B FROM " + Bill.class.getName() + " B WHERE B.id like '%" + search + "%'";
            Query query = session.createQuery(hql);
            list = query.list();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveOrUpdatePhone(Phone phone) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.saveOrUpdate(phone);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updatePhone(Phone phone) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.update(phone);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateBill(Bill bill) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.update(bill);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveOrUpdateBill(Bill bill, Set<Inventory> inventories) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.saveOrUpdate(bill);
            for(Inventory in : inventories){
                if(in != null && in.getCount() > 0){
                    in.setCount(in.getCount()-1);
                    session.update(in);
                    break;
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveOrUpdateDiscount(Discount discount) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.saveOrUpdate(discount);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMultipleCategory(List<Category> list) {
        int size = list.size();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.clear();
        try {

            session.getTransaction().begin();
            for (int i = 0; i < size; i++) {
                session.save(list.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMultiplePhone(List<Phone> list) {
        int size = list.size();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.clear();
        try {

            session.getTransaction().begin();
            for (int i = 0; i < size; i++) {
                session.save(list.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMultipleInventory(List<Inventory> list) {
        int size = list.size();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.clear();
        try {

            session.getTransaction().begin();
            for (int i = 0; i < size; i++) {
                session.save(list.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMultipleBill(List<Bill> list) {
        int size = list.size();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.clear();
        try {

            session.getTransaction().begin();
            for (int i = 0; i < size; i++) {
                session.save(list.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMultiplePayment(List<Payment> list) {
        int size = list.size();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.clear();
        try {

            session.getTransaction().begin();
            for (int i = 0; i < size; i++) {
                session.save(list.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean insertMultipleDiscount(List<Discount> list) {
        int size = list.size();
        Session session = SESSION_FACTORY.getCurrentSession();
        session.clear();
        try {

            session.getTransaction().begin();
            for (int i = 0; i < size; i++) {
                session.save(list.get(i));
                if (i % 20 == 0) {
                    session.flush();
                    session.clear();
                }
            }
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDiscount(Discount discount) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.update(discount);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveOrUpdateCategory(Category category) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.saveOrUpdate(category);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Phone> getListPhone(int page, int max) {
        List<Phone> phones = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Phone.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(max);
            criteria.setFirstResult(page * max);
            phones = criteria.list();
            return phones;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Discount> getListDiscounts(int page, int max) {
        List<Discount> discounts = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Discount.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(max);
            criteria.setFirstResult(page * max);
            discounts = criteria.list();
            return discounts;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Discount> getListDiscountsValid(Date currentDate) {
        List<Discount> discounts = null;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT D FROM " + Discount.class.getName() + " D WHERE D.timeEnd >= :current"; //            
            Query query = session.createQuery(hql);
            query.setDate("current", currentDate);
            discounts = query.list();
            return discounts;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<Payment> getListPayments() {
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

    public static List<Bill> getListBills(int page, int max) {
        List<Bill> bills = null;
        try {
            SESSION_FACTORY.getCurrentSession().beginTransaction();
            Criteria criteria = SESSION_FACTORY.getCurrentSession().createCriteria(Bill.class);
            //criteria.setProjection(Projections.distinct(Projections.property("id")));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.setMaxResults(max);
            criteria.setFirstResult(page * max);
            bills = criteria.list();
            return bills;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteInventory(Inventory inventory) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.delete(inventory);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBill(Bill bill) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.delete(bill);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDiscount(Discount discount) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.delete(discount);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePhone(Phone phone) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            session.delete(phone);
            session.flush();
            session.clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getSumCountSell(int max, List<Date> listDates, int type) {
        int sum = 0;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT sum(P.count) FROM " + Bill.class.getName() + " P "; //
            switch (type) {
                case 0:
                    hql = hql + "WHERE P.dateSell = :dateS";
                    break;
                case 1:
                    hql = hql + "WHERE MONTH(P.dateSell) = MONTH(:dateS) and YEAR(P.dateSell) = YEAR(:dateS)";
                    break;
                case 2:
                    hql = hql + "WHERE YEAR(P.dateSell) = YEAR(:dateS)";
                    break;
                case 3:
                    hql = hql + "WHERE P.dateSell >= :dateB and P.dateSell <= :dateE";
                    break;
            }
            Query query = session.createQuery(hql);
            if (max > 0) {
                query.setMaxResults(max);
            }
            switch (type) {
                case 0:
                case 1:
                case 2:
                    query.setDate("dateS", listDates.get(0));
                    break;
                case 3:
                    query.setDate("dateB", listDates.get(0));
                    query.setDate("dateE", listDates.get(1));
                    break;
            }
            List<?> list = query.list();
            long o = (Long) list.get(0);
            sum = (int) o;
            session.getTransaction().commit();
            return sum;
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }

    public static Map<Object, Object> getBestSell(List<Date> listDates, int type) {
        Map<Object, Object> maps = new HashMap<Object, Object>();
        List<String> name = new ArrayList<>();
        List<Long> data = new ArrayList<>();
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT P.phone.name, sum(P.count) FROM " + Bill.class.getName() + " P ";
            switch (type) {
                case 0:
                    hql = hql + " WHERE P.dateSell = :dateS ";
                    break;
                case 1:
                    hql = hql + " WHERE MONTH(P.dateSell) = MONTH(:dateS) and YEAR(P.dateSell) = YEAR(:dateS) ";
                    break;
                case 2:
                    hql = hql + " WHERE YEAR(P.dateSell) = YEAR(:dateS) ";
                    break;
                case 3:
                    hql = hql + " WHERE P.dateSell >= :dateB and P.dateSell <= :dateE ";
                    break;
            }
            hql = hql + " GROUP BY P.phone.name ORDER BY sum(P.count) DESC ";
            Query query = session.createQuery(hql);
            query.setMaxResults(5);
            switch (type) {
                case 0:
                case 1:
                case 2:
                    query.setDate("dateS", listDates.get(0));
                    break;
                case 3:
                    query.setDate("dateB", listDates.get(0));
                    query.setDate("dateE", listDates.get(1));
                    break;
            }
            List<?> list = query.list();
            for (int i = 0; i < list.size(); i++) {
                Object[] row = (Object[]) list.get(i);
                name.add(row[0].toString());
                data.add((Long) row[1]);
            }
            maps.put("name", name);
            maps.put("data", data);
            session.getTransaction().commit();
            return maps;
        } catch (Exception e) {
            return null;
        }
    }

    public static int getRevenue(List<Date> listDates, int type) {
        int revenue = 0;
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            String hql = "SELECT sum(P.phone.price * P.count - P.phone.price * P.count * P.discount.percentDiscount / 100) FROM " + Bill.class.getName() + " P "; //
            switch (type) {
                case 0:
                    hql = hql + "WHERE P.dateSell = :dateS";
                    break;
                case 1:
                    hql = hql + "WHERE MONTH(P.dateSell) = MONTH(:dateS) and YEAR(P.dateSell) = YEAR(:dateS)";
                    break;
                case 2:
                    hql = hql + "WHERE YEAR(P.dateSell) = YEAR(:dateS)";
                    break;
                case 3:
                    hql = hql + "WHERE P.dateSell >= :dateB and P.dateSell <= :dateE";
                    break;
            }
            Query query = session.createQuery(hql);
            switch (type) {
                case 0:
                case 1:
                case 2:
                    query.setDate("dateS", listDates.get(0));
                    break;
                case 3:
                    query.setDate("dateB", listDates.get(0));
                    query.setDate("dateE", listDates.get(1));
                    break;
            }
            List<?> list = query.list();
            long o = (Long) list.get(0);
            revenue = (int) o;
            session.getTransaction().commit();
            return revenue;
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }

    public static boolean checkLogin(Account account) {
        try {
            Session session = SESSION_FACTORY.getCurrentSession();
            session.getTransaction().begin();
            session.clear();
            String hql = "SELECT P FROM " + Account.class.getName() + " P WHERE P.username = '" + account.getUsername() + "'"; //
            Query query = session.createQuery(hql);
            List<Account> accs = query.list();
            session.flush();
            session.clear();
            session.getTransaction().commit();
            if (accs.size() > 0) {
                Account acc = accs.get(0);
                String accPa = acc.getPassword().trim();
                String accountPa = account.getPassword();
                if (acc != null && accPa.equals(accountPa)) {
                    account.setIsAdmin(acc.isIsAdmin());
                    return true;
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
