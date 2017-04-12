/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import logic.Product;
import logic.Room;
import logic.Store;
import logic.StorePlace;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *
 * @author nickolas
 */
public class StorePlaceDaoImplTest
{
    static StorePlaceDao dao;
    static StorePlace p1, p2, p3;
    static StorePlace nonExisten;
    static List<StorePlace> storePlaces;
    static List<StorePlace> sps;
    public StorePlaceDaoImplTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        dao = new StorePlaceDaoImpl();
        p1 = new StorePlace(1l, 1l, 1l, 5);
        p2 = new StorePlace(2l, 1l, 2l, 10);
        p3 = new StorePlace(3l, 2l, 3l, 100);
        nonExisten = new StorePlace(100l, 3l, 3l, 1000);
        storePlaces = new ArrayList();
        storePlaces.add(p1);
        storePlaces.add(p2);
        storePlaces.add(p3);
        sps = new ArrayList();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        sps.clear();
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void testGetAllStorePlaces() throws Exception {
        System.out.println("getAllStorePlaces");
        sps = dao.getAllStorePlaces();
        assertArrayEquals(storePlaces.toArray(), sps.toArray());
    }

    @Test
    public void testGetStorePlacesByProduct() throws Exception {
        System.out.println("getStorePlacesByProduct");
        Product product = new Product();
        product.setId(1l);
        
        sps = dao.getStorePlacesByProduct(product);
        assertTrue(sps.size() == 1);
        assertTrue(sps.contains(p1));
        
        product.setId(2l);
        sps = dao.getStorePlacesByProduct(product);
        assertTrue(sps.size() == 1);
        assertTrue(sps.contains(p2));
        
        product.setId(3l);
        sps = dao.getStorePlacesByProduct(product);
        assertTrue(sps.size() == 1);
        assertTrue(sps.contains(p3));
        
        product.setId(4l);
        sps = dao.getStorePlacesByProduct(product);
        assertTrue(sps.isEmpty());
    }
    
    
    @Test
    public void testGetStorePlacesByDate() throws Exception {
        System.out.println("getStorePlacesByDate");
        Timestamp date = Timestamp.valueOf("2017-02-27 00:00:00.0");
        sps = dao.getStorePlacesByDate(date);
        
        assertTrue(sps.size() == 2);
        assertTrue(sps.contains(p1));
        assertTrue(sps.contains(p2));
        
        date = Timestamp.valueOf("2017-02-28 00:00:00.0");
        sps = dao.getStorePlacesByDate(date);
        assertTrue(sps.size() == 1);
        assertTrue(sps.contains(p3));
        
        date = Timestamp.valueOf("2017-02-29 00:00:00.0");
        sps = dao.getStorePlacesByDate(date);
        assertTrue(sps.isEmpty());
        
    }


    @Test
    public void testGetStorePlacesByRoom() throws Exception {
        System.out.println("getStorePlacesByRoom");
        Room room = new Room();
        room.setId(1l);
        
        sps = dao.getStorePlacesByRoom(room);
        assertTrue(sps.size() == 2);
        assertTrue(sps.contains(p1));
        assertTrue(sps.contains(p2));
        
        room.setId(2l);
        sps = dao.getStorePlacesByRoom(room);
        assertTrue(sps.size() == 1);
        assertTrue(sps.contains(p3));
   
        room.setId(3l);
        sps = dao.getStorePlacesByRoom(room);
        assertTrue(sps.isEmpty());
    }


    @Test
    public void testGetStorePlacesByStore() throws Exception {
        System.out.println("getStorePlacesByStore");
        Store store = new Store();
        store.setId(1l);
        
        sps = dao.getStorePlacesByStore(store);
        assertArrayEquals(storePlaces.toArray(), sps.toArray());
        
        store.setId(2l);
        sps = dao.getStorePlacesByStore(store);
        assertTrue(sps.isEmpty());
    }

    @Test
    public void testAddAndRemoveStorePlace() throws Exception {
        System.out.println("addStorePlace");
        dao.addStorePlace(nonExisten);
        storePlaces.add(nonExisten);
        sps = dao.getAllStorePlaces();
        assertArrayEquals(storePlaces.toArray(), sps.toArray());
        
        dao.removeStorePlace(nonExisten);
        storePlaces.remove(nonExisten);
        sps = dao.getAllStorePlaces();
        assertArrayEquals(storePlaces.toArray(), sps.toArray());
        
    }


    @Test
    public void testEditStorePlace() throws Exception {
        System.out.println("editStorePlace");
        sps = dao.getAllStorePlaces();
        StorePlace sp = sps.get(0);
        sp.setProductId(2l);
        dao.editStorePlace(sp);
        
        Product product = new Product();
        product.setId(2l);
        sps = dao.getStorePlacesByProduct(product);
        assertTrue(sps.contains(sp));
        
        product.setId(1l);
        sps = dao.getStorePlacesByProduct(product);
        assertFalse(sps.contains(sp));
        
        sp.setProductId(1l);
        dao.editStorePlace(sp);
        sps = dao.getStorePlacesByProduct(product);
        assertTrue(sps.contains(sp));
        
        product.setId(2l);
        sps = dao.getStorePlacesByProduct(product);
        assertFalse(sps.contains(sp));
    }


    
}
