/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import java.sql.Timestamp;
import java.util.List;
import logic.Customer;
import logic.Product;
import java.util.ArrayList;
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
public class ProductDaoImplTest
{
    static List<Product> products;
    static List<Product> prs;
    static Product p1, p2, p3, nonExisten;
    static ProductDaoImpl dao;
    public ProductDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        p1 = new Product(1l, "productname1", null, "food", "someabouttext");
        p2 = new Product(2l, "productname2", null, "food", "someabouttext2");
        p3 = new Product(3l, "productname3", null, "computer", "someabouttext3");
        products = new ArrayList();
        prs = new ArrayList();
        products.add(p1);
        products.add(p2);
        products.add(p3);
        nonExisten = new Product(100l, "asd", 
                new Timestamp(System.currentTimeMillis()), "asd", "lol");
        dao = new ProductDaoImpl();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        prs.clear();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllProduct method, of class ProductDaoImpl.
     */
    @Test
    public void testGetAllProduct() throws Exception {
        System.out.println("getAllProduct");
        prs = dao.getAllProduct();
        for (Product p : prs) {
            System.out.println(p.getId() + p.getName() + p.getType());
        }
        for (Product p : products) {
            System.out.println(p.getId() + p.getName() + p.getType());
        }
        assertArrayEquals(products.toArray(), prs.toArray());
        for (int i = 0; i < 3; ++i) {
            products.get(i).setTimestore(prs.get(i).getTimestore());
        }
    }

    /**
     * Test of getProductsByName method, of class ProductDaoImpl.
     */
    @Test
    public void testGetProductsByName() throws Exception {
        System.out.println("getProductsByName");
        for (Product p : products) {
            prs = dao.getProductsByName(p.getName());
            assertTrue(prs.size() == 1);
            assertTrue(prs.contains(p));
            
        }
        prs = dao.getProductsByName(nonExisten.getName());
        assertTrue(prs.isEmpty());
    }

    /**
     * Test of getProductsByType method, of class ProductDaoImpl.
     */
    @Test
    public void testGetProductsByType() throws Exception {
        System.out.println("getProductsByType");
        
        prs = dao.getProductsByType(p1.getType());
        assertTrue(prs.size() == 2);
        assertTrue(prs.contains(p1));
        assertTrue(prs.contains(p2));
        prs = dao.getProductsByType(p2.getType());
        assertTrue(prs.size() == 2);
        assertTrue(prs.contains(p1));
        assertTrue(prs.contains(p2));
        prs = dao.getProductsByType(p3.getType());
        assertTrue(prs.size() == 1);
        assertTrue(prs.contains(p3));
        
        prs = dao.getProductsByName(nonExisten.getType());
        assertTrue(prs.isEmpty());
    }

    /**
     * Test of getProductsByDate method, of class ProductDaoImpl.
     */
    @Ignore
    @Test
    public void testGetProductsByDate() throws Exception {
        System.out.println("getProductsByDate");
        for (Product p : products) {
            prs = dao.getProductsByDate(p.getTimestore());
            System.out.println(p.getTimestore());
            System.out.println(products.get(0).getTimestore());
            System.out.println(prs.size());
            assertTrue(prs.size() == 1);
            assertTrue(p.getTimestore().equals(prs.get(0)));
        }
        prs = dao.getProductsByDate(nonExisten.getTimestore());
        assertTrue(prs.isEmpty());
    }

    /**
     * Test of saveProduct method, of class ProductDaoImpl.
     */
    @Test
    public void testSaveAndRemoveProduct() throws Exception {
        System.out.println("saveProduct");
        dao.saveProduct(nonExisten);
        prs = dao.getAllProduct();
        products.add(nonExisten);
        assertArrayEquals(products.toArray(), prs.toArray());
        products.remove(nonExisten);
        dao.removeProduct(nonExisten);
        prs = dao.getAllProduct();
        assertArrayEquals(products.toArray(), prs.toArray());
    }

    /**
     * Test of editProduct method, of class ProductDaoImpl.
     */
    @Test
    public void testEditProduct() throws Exception {
        System.out.println("editProduct");
        Product p;
        prs = dao.getProductsByName(p1.getName());
        assertTrue(prs.contains(p1));
        assertTrue(prs.size() == 1);
        p = prs.get(0);
        String nameNew = "newName", nameOld = p.getName();
        p.setName(nameNew);
        dao.editProduct(p);
        
        prs = dao.getProductsByName(p1.getName());
        assertTrue(prs.isEmpty());
        prs = dao.getProductsByName(nameNew);
        assertTrue(prs.contains(p));
        assertTrue(prs.size() == 1);
        p.setName(nameOld);
        dao.editProduct(p);
        
        prs = dao.getProductsByName(p1.getName());
        assertTrue(prs.contains(p1));
        assertTrue(prs.size() == 1);
        prs = dao.getProductsByName(nameNew);
        assertTrue(prs.isEmpty());
    }

    /**
     * Test of getProductsByPurcher method, of class ProductDaoImpl.
     */
    @Test
    public void testGetProductsByPurcher() throws Exception {
        System.out.println("getProductsByPurcher");
        Customer c = new Customer();
        c.setId(1l);
        prs = dao.getProductsByPurcher(c);
        assertTrue(prs.contains(p3));
        assertTrue(prs.size() == 1);
        c.setId(2l);
        prs = dao.getProductsByPurcher(c);
        assertTrue(prs.contains(p1));
        assertTrue(prs.size() == 1);
        
        c.setId(3l);
        prs = dao.getProductsByPurcher(c);
        assertTrue(prs.isEmpty());
    }

    /**
     * Test of getProductsBySaler method, of class ProductDaoImpl.
     */
    @Test
    public void testGetProductsBySaler() throws Exception {
        System.out.println("getProductsBySaler");
        Customer c = new Customer();
        c.setId(1l);
        prs = dao.getProductsBySaler(c);
        assertTrue(prs.contains(p1));
        assertTrue(prs.contains(p2));
        assertTrue(prs.size() == 2);

        c.setId(2l);
        prs = dao.getProductsBySaler(c);
        assertTrue(prs.isEmpty());
        
        c.setId(3l);
        prs = dao.getProductsBySaler(c);
        assertTrue(prs.contains(p3));
        assertTrue(prs.size() == 1);
        
    }
    
}
