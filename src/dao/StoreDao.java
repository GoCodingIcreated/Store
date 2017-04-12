/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import logic.*;
import java.sql.SQLException;

/**
 *
 * @author nickolas
 */
public interface StoreDao
{
    public List<Store> getAllStores() throws SQLException;
    public List<Store> getStoreByRoom(Room room) throws SQLException;
    public Double getCapacityByStore(Store store) throws SQLException;
    public Double getFreeSpaceByStore(Store store) throws SQLException;
    public Double getTotalCapacity() throws SQLException;
    public Double getTotalFreeSpace() throws SQLException;
    public void addStore(Store store) throws SQLException;
    public void removeStore(Store store) throws SQLException;
    public void editStore(Store store) throws SQLException;
}
