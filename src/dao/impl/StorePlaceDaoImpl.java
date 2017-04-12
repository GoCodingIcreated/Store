/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import dao.StorePlaceDao;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import logic.Product;
import logic.Room;
import logic.Store;
import logic.StorePlace;
import org.hibernate.Session;
import util.HibernateUtil;
/**
 *
 * @author nickolas
 */
public class StorePlaceDaoImpl implements StorePlaceDao
{
    public List<StorePlace> getAllStorePlaces() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StorePlace> sp;
        sp = session.createCriteria(StorePlace.class).list();
        session.close();
        return sp;
    }
    public List<StorePlace>  getStorePlacesByProduct(Product product) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StorePlace> sp;
        sp = session.createSQLQuery("SELECT * "
                + "FROM stored_place "
                + "WHERE product_id = :ID")
                .addEntity(StorePlace.class)
                .setLong("ID", product.getId())
                .list();
        session.close();
        return sp;
    }
    public List<StorePlace>  getStorePlacesByDate(Timestamp date) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StorePlace> sp;
        sp = session.createSQLQuery("SELECT * "
                + "FROM stored_place "
                + "WHERE time_arrived = :TIME")
                .addEntity(StorePlace.class)
                .setString("TIME", date.toString())
                .list();
        session.close();
        return sp;
    }
    public List<StorePlace>  getStorePlacesByRoom(Room room) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StorePlace> sp;
        sp = session.createSQLQuery("SELECT * "
                + "FROM stored_place "
                + "WHERE room_id = :ID")
                .addEntity(StorePlace.class)
                .setLong("ID", room.getId())
                .list();
        session.close();
        return sp;
    }
    public List<StorePlace>  getStorePlacesByStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StorePlace> sp;
        sp = session.createSQLQuery("SELECT stored_place.* "
                + "FROM stored_place JOIN Room ON stored_place.room_id = room.id "
                + "WHERE room.store_id = :ID")
                .addEntity(StorePlace.class)
                .setLong("ID", store.getId())
                .list();
        session.close();
        return sp;
    }
    public void addStorePlace(StorePlace sp) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(sp);
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
    public void editStorePlace(StorePlace sp) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(sp);
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
    public void removeStorePlace(StorePlace sp) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(sp);
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
