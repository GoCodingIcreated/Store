/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import dao.*;
import logic.*;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
/**
 *
 * @author nickolas
 */

public interface StorePlaceDao
{
    public List<StorePlace> getAllStorePlaces() throws SQLException;
    public List<StorePlace>  getStorePlacesByProduct(Product product) throws SQLException;
    public List<StorePlace>  getStorePlacesByDate(Timestamp date) throws SQLException;
    public List<StorePlace>  getStorePlacesByRoom(Room room) throws SQLException;
    public List<StorePlace>  getStorePlacesByStore(Store store) throws SQLException;
    public void addStorePlace(StorePlace sp) throws SQLException;
    public void editStorePlace(StorePlace sp) throws SQLException;
    public void removeStorePlace(StorePlace sp) throws SQLException;
}
