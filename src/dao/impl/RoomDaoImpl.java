/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import java.math.BigDecimal;
import dao.RoomDao;
import java.sql.SQLException;
import java.util.List;
import logic.Product;
import logic.Room;
import logic.Store;
import logic.StorePlace;
import org.hibernate.Session;
import util.HibernateUtil;
import org.hibernate.type.DoubleType;
/**
 *
 * @author nickolas
 */
public class RoomDaoImpl implements RoomDao
{
    public List<Room> getAllRooms() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Room> rooms = session.createCriteria(Room.class).list();
        session.close();
        return rooms;
    }
    public List<Room> getRoomsByStore(Store store) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Room> rooms;
        rooms = session.createSQLQuery("SELECT *\n" +
                            "FROM Room\n" +
                            "WHERE store_id = :ID")
                .addEntity(Room.class)
                .setLong("ID", store.getId())
                .list();
        session.close();
        return rooms;
    }
    public List<Room> getRoomsByProduct(Product product) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Room> rooms;
        rooms = session.createSQLQuery("SELECT room.*\n" +
                        "FROM Room JOIN Stored_place"
                        + " ON room.id = stored_place.room_id JOIN Product"
                        + " ON stored_place.product_id = product.id\n"
                        + "WHERE product.id = :ID")
                .addEntity(Room.class)
                .setLong("ID", product.getId())
                .list();
        session.close();
        return rooms;
    }
    public Room getRoomByStorePlace(StorePlace storePlace) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Room room = (Room)session.createSQLQuery("SELECT * FROM Room WHERE id = :ID")
                .addEntity(Room.class)
                .setLong("ID", storePlace.getRoomId())
                .list()
                .get(0);
        session.close();
        return room;
    }
    public void addRoom(Room room) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(room);
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
    public void removeRoom(Room room) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(room);
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
    public void editRoom(Room room) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(room);
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
    public Double getFreeSpace(Room room) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Double usedSpace;
        usedSpace = (Double)session.createSQLQuery(
                        "SELECT COALESCE(SUM(count), 0) as c\n" +
                        "FROM Stored_place\n" +
                        "WHERE room_id = :ID")
                    .addScalar("c", DoubleType.INSTANCE)
                    .setLong("ID", room.getId())
                    .uniqueResult();
        session.close();
        System.out.println(usedSpace);
        return room.getCapacity() - (usedSpace);
    }
}
