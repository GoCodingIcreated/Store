/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.*;
import logic.*;
import util.Factory;
import util.HibernateUtil;
import dao.CustomerDao;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author nickolas
 */
public class CustomerDaoImplTest
{
    static CustomerDao dao;
    static ArrayList<Customer> customers;
    static Customer c1, c2, c3, c4;
    static Customer nonExisten;
    public CustomerDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        customers = new ArrayList();
        dao = Factory.getInstance().getCustomerDao();
        c1 = new Customer(1l, "1111111111", "moscow", "name1");
        c2 = new Customer(2l, "2222222222", "piter", "name2");
        c3 = new Customer(3l, "3333333333", "mp", "name3");
        c4 = new Customer(4l, "4444444444", "city4", "name4");
        nonExisten = new Customer(5l, "5555", "asdas", "nameasd");
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of getAllCustomers method, of class CustomerDaoImpl.
     */
    
    
    @Test
    public void testGetAllCustomers() throws Exception {
        System.out.println("getAllCustomers");     
        List<Customer> expResult = customers;
        List<Customer> result = dao.getAllCustomers();
        System.out.println(((Customer)(result.toArray()[0])).getId());
        assertArrayEquals(expResult.toArray(), result.toArray());
        
    }

    /**
     * Test of getCustomersByName method, of class CustomerDaoImpl.
     */
   
    
    
    @Test
    public void testGetCustomersByName() throws Exception {
        System.out.println("getCustomersByName");
        
        String names[] = {"name1", "name2", "name3", "name4"} ;
        List<Customer> expResult = new ArrayList<Customer>();        
        List<Customer> result;
        int i = 0;
        for (String name : names) {
            expResult.clear();
            expResult.add(customers.get(i));
            ++i;
            result = dao.getCustomersByName(name);
            assertArrayEquals(expResult.toArray(), result.toArray());
            result = dao.getCustomersByName(name);
            assertArrayEquals(expResult.toArray(), result.toArray());
            result = dao.getCustomersByName(name.toUpperCase());
            assertArrayEquals(expResult.toArray(), result.toArray());
            result = dao.getCustomersByName(name + '1');
            assertTrue(result.isEmpty());
        }
    }

    /**
     * Test of saveCustomer method, of class CustomerDaoImpl.
     */
    
    
    
    
    
    @Test
    public void testSaveNonExisten() throws Exception {
        System.out.println("testSaveNonExisten");
        Customer customer = nonExisten;
        dao.saveCustomer(customer);
        List<Customer> c = dao.getCustomersByName(nonExisten.getName());
        assertTrue(c.contains(customer) && c.size() == 1);
        dao.removeCustomer(customer);
        c = dao.getCustomersByName(nonExisten.getName());
        assertFalse(c.contains(customer) || c.size() > 0);
    }
    
    
   
    @Test
    public void testSaveExisten() throws Exception {
        /*Customer customer = new Customer(c1);
        List<Customer> c = dao.getCustomersByName(customer.getName());
        dao.removeCustomer(c.get(1));
        return;*/
        System.out.println("testSaveExisten");
        Customer customer = new Customer(c1), customer2;
        dao.saveCustomer(customer);
        List<Customer> c = dao.getCustomersByName(customer.getName());
        assertTrue(c.contains(customer));
        assertTrue(c.size() == 2);
        assertFalse(c.get(0).equals(c.get(1)));
        customer2 = c.get(1);
        c = dao.getAllCustomers();
        assertTrue(c.size() == 5);
        dao.removeCustomer(customer2);
        c = dao.getCustomersByName(customer.getName());

        assertTrue(c.size() == 1);
        assertTrue(c.contains(c1));
    }
    
    @Test(expected = Exception.class)
    public void testRemoveNonExisten() throws Exception {
        System.out.println("testRemoveNonExisten");
        Customer customer = nonExisten;
        dao.removeCustomer(customer);
        List<Customer> c = dao.getAllCustomers();
        assertFalse(c.contains(customer));
        assertTrue(c.size() == 4);
        assertArrayEquals(customers.toArray(), c.toArray());
    }
    
    
    @Test
    public void testRemoveExisten() throws Exception {
        System.out.println("testSaveExisten");
        Customer customer = new Customer(c1);
        dao.saveCustomer(customer);
        List<Customer> c = dao.getAllCustomers();
        assertTrue(c.contains(customer));
        assertTrue(c.size() == 5);
        c = dao.getCustomersByName(c1.getName());
        assertTrue(c.size() == 2);
        
        dao.removeCustomer(customer);
        c = dao.getAllCustomers();
        assertFalse(c.contains(customer));
        assertTrue(c.size() == 4);
        c = dao.getCustomersByName(c1.getName());
        assertTrue(c.size() == 1);
        
        c = dao.getCustomersByName(c1.getName());
        assertTrue(c.contains(c1));
        assertTrue(c.size() == 1);
    }
    
    
    @Test
    public void testSameNames() throws Exception {
        System.out.println("testSameNames");
        
        Customer customer = new Customer(10l, c1.getPhone(), c1.getAdres(), c1.getName());
        dao.saveCustomer(customer);
        List<Customer> c = dao.getAllCustomers();
        assertTrue(c.contains(customer));
        assertTrue(c.contains(c1) && c.contains(c2) && c.contains(c3));
        assertTrue(c.contains(c4));
        assertTrue(c.size() == 5);
        c = dao.getCustomersByName(c1.getName());
        assertTrue(c.contains(c1) && c.contains(customer));
        assertTrue(c.size() == 2);
        dao.removeCustomer(customer);
        c = dao.getCustomersByName(c1.getName());
        assertTrue(c.size() == 1);
        assertTrue(c.contains(c1));
        c = dao.getAllCustomers();
        assertFalse(c.contains(customer));
    }
    
    
    @Test(expected = Exception.class)
    public void testEditNonExist() throws Exception {
        System.out.println("testEditNonExist");
        dao.editCustomer(nonExisten);
        List<Customer> c = dao.getAllCustomers();
        assertArrayEquals(customers.toArray(), c.toArray());
    }
    
    
    @Test
    public void testEditExisten() throws Exception {
        System.out.println("testEditExisten");
        List<Customer> c = dao.getCustomersByName(c1.getName());
        assertTrue(c.contains(c1) && c.size() == 1);
        
        Customer customer = new Customer(c.get(0));
        Customer customer2 = new Customer(customer);
        customer2.setName("newName");
        dao.editCustomer(customer2);
        c = dao.getCustomersByName(customer.getName());
        assertTrue(c.isEmpty());
        
        dao.editCustomer(customer);
        c = dao.getCustomersByName(customer2.getName());
        assertFalse(c.contains(customer2) || c.size() > 0);
        c = dao.getCustomersByName(customer.getName());
        assertTrue(c.contains(customer) && c.size() == 1);
        
    }
}
