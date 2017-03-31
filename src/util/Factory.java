/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import dao.*;
import dao.impl.*;

/**
 *
 * @author nickolas
 */
public class Factory {
  
    private static CustomerDao customerDao = null;
    private static ProductDao productDao = null;
    private static StoreDao storeDao = null;
    private static StorePlaceDao storePlaceDao = null;
    private static TransactionDao transactionDao = null;
    private static RoomDao roomDao = null;
    private static Factory instance = null;

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public CustomerDao getCustomerDao() {
        if (customerDao == null) {
            customerDao = new CustomerDaoImpl();
        }
        return customerDao;
    }
    public ProductDao getProductDao() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }
    public RoomDao getRoomDao() {
        if (roomDao == null) {
            roomDao = new RoomDaoImpl();
        }
        return roomDao;
    }
    public StoreDao getStoreDao() {
        if (storeDao == null) {
            storeDao = new StoreDaoImpl();
        }
        return storeDao;
    }
    public StorePlaceDao getStorePlaceDao() {
        if (storePlaceDao == null) {
            storePlaceDao = new StorePlaceDaoImpl();
        }
        return storePlaceDao;
    }
    public TransactionDao getTransactionDao() {
        if (transactionDao == null) {
            transactionDao = new TransactionDaoImpl();
        }
        return transactionDao;
    }


}