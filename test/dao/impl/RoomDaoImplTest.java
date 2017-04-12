/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import logic.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.Assert.*;

/**
 *
 * @author nickolas
 */
public class RoomDaoImplTest
{
    static RoomDaoImpl dao;
    static Room r1, r2, r3, r4, r5, r6, r7, r8;
    static List<Room> rooms;
    static List<Room> rs;
    static List<Room> tempList;
    static Room nonExisten;
    public RoomDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
// Long id, Long storeId, int number, int capacity) {
        r1 = new Room(1l, 1l, 101, 1000);
        r2 = new Room(2l, 1l, 102, 2000);
        r3 = new Room(3l, 2l, 101, 1500);
        r4 = new Room(4l, 3l, 101, 3000);
        r5 = new Room(5l, 4l, 1, 1200);
        r6 = new Room(6l, 5l, 201, 5000);
        r7 = new Room(7l, 6l, 201, 7000);
        r8 = new Room(8l, 5l, 202, 6000);
        dao = new RoomDaoImpl();
        tempList = new ArrayList();
        rooms = new ArrayList();
        rooms.add(r1);
        rooms.add(r2);
        rooms.add(r3);
        rooms.add(r4);
        rooms.add(r5);
        rooms.add(r6);
        rooms.add(r7);
        rooms.add(r8);
        nonExisten = new Room(10l, 6l, 202, 1);
    }
    
    @Before
    public void setUp() {
        tempList.clear();
    }

    @Test
    public void testGetAllRooms() throws Exception {
        System.out.println("getAllRoom");
        rs = dao.getAllRooms();
        assertArrayEquals(rooms.toArray(), rs.toArray());
        
    }

    @Test
    public void testGetRoomsByStore() throws Exception {
        System.out.println("getRoomsByStore");
        Store store = new Store();
        for (int i = 0; i < 6; ++i) {
            store.setId((long)i);
            rs = dao.getRoomsByStore(store);
            for (Room r : rooms) {
                if (r.getStoreId() == i) {
                    tempList.add(r);
                }
            }          
            assertArrayEquals(tempList.toArray(), rs.toArray());
            tempList.clear();
        }
    }

    @Test
    public void testGetRoomsByProduct() throws Exception {
        Product product = new Product();
        product.setId(1l);
        rs = dao.getRoomsByProduct(product);
        tempList.add(r1);
        assertArrayEquals(tempList.toArray(), rs.toArray());
        product.setId(2l);
        rs = dao.getRoomsByProduct(product);
        assertArrayEquals(tempList.toArray(), rs.toArray());
        
        product.setId(3l);
        tempList.clear();
        tempList.add(r2);
        rs = dao.getRoomsByProduct(product);
        assertArrayEquals(tempList.toArray(), rs.toArray());
        
    }

    @Test
    public void testGetRoomByStorePlace() throws Exception {
        System.out.println("getRoomByStorePlace");
        StorePlace sp = new StorePlace();
        sp.setId(1l);
        sp.setRoomId(1l);
        Room r = dao.getRoomByStorePlace(sp);
        assertEquals(r1, r);
        
        sp.setId(2l);
        
        r = dao.getRoomByStorePlace(sp);
        assertEquals(r1, r);
        
        sp.setId(3l);
        sp.setRoomId(2l);
        r = dao.getRoomByStorePlace(sp);
        assertEquals(r2, r);
        
    }

    @Test
    public void testAddRoom() throws Exception {
        System.out.println("addRoom");
        dao.addRoom(nonExisten);
        rooms.add(nonExisten);
        rs = dao.getAllRooms();
        assertArrayEquals(rooms.toArray(), rs.toArray());
        dao.removeRoom(nonExisten);
        rooms.remove(nonExisten);
        rs = dao.getAllRooms();
        assertArrayEquals(rooms.toArray(), rs.toArray());
    }

    @Test(expected = Exception.class)
    public void testRemoveRoom() throws Exception {
        System.out.println("removeRoom");
        dao.removeRoom(nonExisten);
        fail("failed, exception should be");
    }

    @Test
    public void testEditRoom() throws Exception {
        System.out.println("editRoom");
        rs = dao.getAllRooms();
        assertTrue(rs.size() > 0);
        Room r = rs.get(0);
        Double prevCapacity = r.getCapacity();
        Double newCapacity = 10000.0;
        assertFalse(prevCapacity.equals(newCapacity));
        r.setCapacity(newCapacity);
        dao.editRoom(r);
        rs = dao.getAllRooms();
        r = rs.get(0);
        assertEquals(r.getCapacity(), newCapacity);
        r.setCapacity(prevCapacity);
        dao.editRoom(r);
        rs = dao.getAllRooms();
        r = rs.get(0);
        assertEquals(r.getCapacity(), prevCapacity);
    }

    @Test
    public void testGetFreeSpace() throws Exception {
        System.out.println("getFreeSpace");
        Double usedSpaceRoom1 = new Double(15), usedSpaceRoom2 = new Double(100);
        Double freeSpace = dao.getFreeSpace(r1).doubleValue();
        assertEquals((Double)(r1.getCapacity() - usedSpaceRoom1), freeSpace);
        freeSpace = dao.getFreeSpace(r2);
        assertEquals((Double)(r2.getCapacity() - usedSpaceRoom2), freeSpace);
        freeSpace = dao.getFreeSpace(r3);
        assertEquals(r3.getCapacity(), freeSpace);
    }
    
}
