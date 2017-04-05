/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import logic.*;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author nickolas
 */
public interface RoomDao
{
    public List<Room> getAllRooms() throws SQLException;
    public List<Room> getRoomsByStore(Store store) throws SQLException;
    public List<Room> getRoomsByProduct(Product product) throws SQLException;
    public Room getRoomByStorePlace(StorePlace storePlace) throws SQLException;
    public void addRoom(Room room) throws SQLException;
    public void removeRoom(Room room) throws SQLException;
    public void editRoom(Room room) throws SQLException;
    public Double getFreeSpace(Room room) throws SQLException;
}
