/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import logic.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.Assert.*;

/**
 *
 * @author nickolas
 */
public class StoreDaoImplTest
{
    static StoreDaoImpl dao;
    static Store s1, s2, s3, s4, s5, s6;
    static Store nonExisten;
    static List<Store> stores;
    static List<Store> sts;
    public StoreDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dao = new StoreDaoImpl();
        s1 = new Store(1l, "storeadres1");
        s2 = new Store(2l, "storeadres2");
        s3 = new Store(3l, "storeadres3");
        s4 = new Store(4l, "storeadres4");
        s5 = new Store(5l, "storeadres5");
        s6 = new Store(6l, "storeadres6");
        stores = new ArrayList();
        stores.add(s1);
        stores.add(s2);
        stores.add(s3);
        stores.add(s4);
        stores.add(s5);
        stores.add(s6);
        nonExisten = new Store(10l, "newAdres");
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllStores() throws Exception {
        System.out.println("getAllStores");
        sts = dao.getAllStores();
        assertArrayEquals(stores.toArray(), sts.toArray());
    }

    @Test
    public void testGetStoreByRoom() throws Exception {
        Room room = new Room();
        room.setId(1l);
        room.setStoreId(1l);
        sts = dao.getStoreByRoom(room);
        assertTrue(sts.contains(s1));
        assertTrue(sts.size() == 1);
        room.setId(4l);
        room.setStoreId(3l);
        sts = dao.getStoreByRoom(room);
        assertTrue(sts.contains(s3));
        assertTrue(sts.size() == 1);
    }

    @Test
    public void testGetCapacityByStore() throws Exception {
        System.out.println("getCapacityByStore");
        Double expected = 3000.0;
        Double current = dao.getCapacityByStore(s1);
        assertEquals(expected, current);
        expected = 1500.0;
        current = dao.getCapacityByStore(s2);
        assertEquals(expected, current);
        expected = 0.0;
        current = dao.getCapacityByStore(nonExisten);
        assertEquals(expected, current);
    }

    @Test
    public void testGetFreeSpaceByStore() throws Exception {
        System.out.println("getFreeSpaceByStore");
        Double expected = 3000.0 - 115.0;
        Double current = dao.getFreeSpaceByStore(s1);
        assertEquals(expected, current);
        expected = 1500.0;
        current = dao.getFreeSpaceByStore(s2);
        assertEquals(expected, current);
        expected = 0.0;
        current = dao.getFreeSpaceByStore(nonExisten);
        assertEquals(expected, current);
    }

    @Test
    public void testGetTotalCapacity() throws Exception {
        System.out.println("getTotalCapacity");
        Double expected = 26700.0;
        Double current = dao.getTotalCapacity();
        assertEquals(expected, current);
    }

    @Test
    public void testGetTotalFreeSpace() throws Exception {
        System.out.println("getTotalFreeSpace");
        Double expected = 26700.0 - 115.0;
        Double current = dao.getTotalFreeSpace();
        assertEquals(expected, current);
    }

    @Test
    public void testAddStore() throws Exception {
        System.out.println("addStore");
        dao.addStore(nonExisten);
        sts = dao.getAllStores();
        stores.add(nonExisten);
        assertArrayEquals(stores.toArray(), sts.toArray());
        stores.remove(nonExisten);
        dao.removeStore(nonExisten);
        sts = dao.getAllStores();
        assertArrayEquals(stores.toArray(), sts.toArray());
    }

    @Test(expected = Exception.class)
    public void testRemoveStore() throws Exception {
        System.out.println("removeStore");
        dao.removeStore(nonExisten);
        fail("failed");
    }

    @Test
    public void testEditStore() throws Exception {
        System.out.println("editStore");
        sts = dao.getAllStores();
        assertTrue(sts.size() > 0);
        Store s = sts.get(0);
        String oldAdres = s.getAdres(), newAdres = "newadres";
        s.setAdres(newAdres);
        dao.editStore(s);
        sts = dao.getAllStores();
        assertTrue(sts.contains(s));
        assertFalse(sts.contains(s1));
        s.setAdres(oldAdres);
        dao.editStore(s);
        sts = dao.getAllStores();
        assertTrue(sts.contains(s1));
    }
    
}
