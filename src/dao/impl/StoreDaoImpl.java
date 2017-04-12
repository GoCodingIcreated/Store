/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import dao.StoreDao;
import java.sql.SQLException;
import java.util.List;
import logic.Room;
import logic.Store;
import org.hibernate.Session;
import org.hibernate.type.DoubleType;
import util.HibernateUtil;
/**
 *
 * @author nickolas
 */
public class StoreDaoImpl implements StoreDao
{
    public List<Store> getAllStores() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Store> stores;
        stores = session.createCriteria(Store.class).list();
        session.close();
        return stores;
    }
    public List<Store> getStoreByRoom(Room room) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Store> stores;
        stores = session.createSQLQuery("SELECT *"
                            + " FROM Store"
                            + " WHERE id = :ID")
                            .addEntity(Store.class)
                            .setLong("ID", room.getStoreId())
                            .list();
        session.close();
        return stores;
    }
    public Double getCapacityByStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double capacity;
        capacity = (Double)session.createSQLQuery("SELECT COALESCE(SUM(capacity), 0)\n" +
                                "FROM Room\n" +
                                "WHERE store_id = :ID")
                        .setLong("ID", store.getId())
                        .list()
                        .get(0);
        session.close();
        return capacity;
    }
    public Double getFreeSpaceByStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double capacity, usedSpace;
        capacity = getCapacityByStore(store);
        usedSpace = (Double)session.createSQLQuery("SELECT COALESCE(SUM(count), 0) as c\n"
                            + "FROM Stored_place JOIN Room "
                            + "ON Stored_place.room_id = Room.id JOIN Store"
                            + " ON Store.id = Room.store_id\n" 
                            + "WHERE Store.id = :ID")
                        .addScalar("c", DoubleType.INSTANCE)
                        .setLong("ID", store.getId())
                        .uniqueResult();
        session.close();
        return capacity - usedSpace;
    }
    public Double getTotalCapacity() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double totalCapacity;
        totalCapacity = (Double)session.createSQLQuery("SELECT COALESCE(SUM(capacity), 0) as c\n"
                                + "FROM Room")                           
                        .addScalar("c", DoubleType.INSTANCE)
                        .uniqueResult();
        session.close();
        return totalCapacity;
    }
    public Double getTotalFreeSpace() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double totalUsed, totalCapacity;
        totalCapacity = getTotalCapacity();
        totalUsed = (Double)session.createSQLQuery("SELECT COALESCE(SUM(count), 0) as c\n"
                                + "FROM Stored_place")
                        .addScalar("c", DoubleType.INSTANCE)
                        .uniqueResult();
        session.close();
        return totalCapacity - totalUsed;
    }
    public void addStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(store);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        finally {
            
            session.close();
        }
    }
    public void removeStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(store);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        finally {
            
            session.close();
        }
    }
    public void editStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(store);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        finally {
            
            session.close();
        }
    }
}
