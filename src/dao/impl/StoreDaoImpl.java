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
                            .setLong("ID", room.getId())
                            .list();
        session.close();
        return stores;
    }
    public Double getCapacityByStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double capacity;
        capacity = (Double)session.createSQLQuery("SELECT SUM(capacity)\n" +
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
        usedSpace = (Double)session.createSQLQuery("SELECT SUM(count)\n"
                            + "FROM Stored_place JOIN Room "
                            + "ON Stored_place.room_id = Room.id JOIN Store"
                            + " ON Store.id = Room.store_id\n" 
                            + "WHERE Store.id = :ID")
                        .setLong("ID", store.getId())
                        .list()
                        .get(0);
        session.close();
        return capacity - usedSpace;
    }
    public Double getTotalCapacity() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double totalCapacity;
        totalCapacity = (Double)session.createSQLQuery("SELECT SUM(capacity)\n"
                                + "FROM Room")                           
                            .list()
                            .get(0);
        session.close();
        return totalCapacity;
    }
    public Double getTotalFreeSpace() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double totalUsed, totalCapacity;
        totalCapacity = getTotalCapacity();
        totalUsed = (Double)session.createSQLQuery("SELECT SUM(count)\n"
                                + "FROM Stored_place")
                            .list()
                            .get(0);
        session.close();
        return totalCapacity - totalUsed;
    }
}
