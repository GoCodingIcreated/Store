/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;
import logic.*;
import org.junit.Ignore;
import static org.junit.Assert.*;

/**
 *
 * @author nickolas
 */
public class TransactionDaoImplTest
{
    static TransactionDaoImpl dao;
    static Transaction t1, t2, t3, t4, t5;
    static Transaction nonExisten;
    static List<Transaction> trans;
    static List<Transaction> trs;
    
    public TransactionDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dao = new TransactionDaoImpl();
        t1 = new Transaction(1l, 1l, 1l, false, 10.0);
        t2 = new Transaction(2l, 2l, 1l, false, 10.0);
        t3 = new Transaction(3l, 1l, 2l, true, 5.0);
        t4 = new Transaction(4l, 3l, 3l, false, 200.0);
        t5 = new Transaction(5l, 3l, 1l, true, 100.0);
        nonExisten = new Transaction(10l, 1l, 1l, false, 10.0);
        
        trans = new ArrayList();
        trans.add(t1);
        trans.add(t2);
        trans.add(t3);
        trans.add(t4);
        trans.add(t5);
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllTransactions() throws Exception {
        System.out.println("getAllTransaction");
        trs = dao.getAllTransactions();
        assertArrayEquals(trans.toArray(), trs.toArray());
    }

    @Test
    public void testGetTransactionsByCustomer() throws Exception {
        System.out.println("getTransactionsByCustomer");
        Customer customer = new Customer();
        customer.setId(1l);
        trs = dao.getTransactionsByCustomer(customer);
        assertTrue(trs.contains(t1));
        assertTrue(trs.contains(t2));
        assertTrue(trs.contains(t5));
        assertTrue(trs.size() == 3);
        customer.setId(2l);
        trs = dao.getTransactionsByCustomer(customer);
        assertTrue(trs.contains(t3));
        assertTrue(trs.size() == 1);
        customer.setId(10l);
        trs = dao.getTransactionsByCustomer(customer);
        assertTrue(trs.isEmpty());
    }

    @Test
    public void testGetTransactionsByProduct() throws Exception {
        System.out.println("getTransactionsByProduct");
        Product product = new Product();
        product.setId(1l);
        trs = dao.getTransactionsByProduct(product);
        assertTrue(trs.contains(t1));
        assertTrue(trs.contains(t3));
        assertTrue(trs.size() == 2);
        product.setId(3l);
        trs = dao.getTransactionsByProduct(product);
        assertTrue(trs.contains(t4));
        assertTrue(trs.contains(t5));
        assertTrue(trs.size() == 2);
        product.setId(10l);
        trs = dao.getTransactionsByProduct(product);
        assertTrue(trs.isEmpty());
    }

    @Test
    public void testGetAllPurchases() throws Exception {
        System.out.println("getAllPurchases");
        trs = dao.getAllPurchases();
        assertTrue(trs.contains(t3));
        assertTrue(trs.contains(t5));
        assertTrue(trs.size() == 2);
    }

    @Test
    public void testGetAllSales() throws Exception {
        System.out.println("getAllSales");
        trs = dao.getAllSales();
        assertTrue(trs.contains(t1));
        assertTrue(trs.contains(t2));
        assertTrue(trs.contains(t4));
        assertTrue(trs.size() == 3);
    }

    @Test
    public void testGetPurchasesByCustomer() throws Exception {
        System.out.println("getPurchasesByCustomer");
        Customer customer = new Customer();
        customer.setId(1l);
        trs = dao.getPurchasesByCustomer(customer);
        assertTrue(trs.size() == 1);
        assertTrue(trs.contains(t5));
        customer.setId(2l);
        trs = dao.getPurchasesByCustomer(customer);
        assertTrue(trs.size() == 1);
        assertTrue(trs.contains(t3));
        customer.setId(3l);
        trs = dao.getPurchasesByCustomer(customer);
        assertTrue(trs.isEmpty());
    }

    @Test
    public void testGetSalesByCustomer() throws Exception {
        System.out.println("getSalesByCustomer");
        Customer customer = new Customer();
        customer.setId(1l);
        trs = dao.getSalesByCustomer(customer);
        assertTrue(trs.size() == 2);
        assertTrue(trs.contains(t1));
        assertTrue(trs.contains(t2));
        
        customer.setId(3l);
        trs = dao.getSalesByCustomer(customer);
        assertTrue(trs.size() == 1);
        assertTrue(trs.contains(t4));
        
        customer.setId(2l);
        trs = dao.getSalesByCustomer(customer);
        assertTrue(trs.isEmpty());
    }

    @Test
    public void testGetPurchasesByProduct() throws Exception {
        System.out.println("getPurchasesByProduct");
        Product product = new Product();
        product.setId(1l);
        trs = dao.getPurchasesByProduct(product);
        assertTrue(trs.size() == 1);
        assertTrue(trs.contains(t3));
        product.setId(2l);
        trs = dao.getPurchasesByProduct(product);
        assertTrue(trs.isEmpty());
        
    }

    @Test
    public void testGetSalesByProduct() throws Exception {
        System.out.println("getSalesByProduct");
        Product product = new Product();
        product.setId(1l);
        trs = dao.getSalesByProduct(product);
        assertTrue(trs.size() == 1);
        assertTrue(trs.contains(t1));
        product.setId(10l);
        trs = dao.getPurchasesByProduct(product);
        assertTrue(trs.isEmpty());
        
    }

    @Test
    public void testAddTransaction() throws Exception {
        System.out.println("addTransaction");
        dao.addTransaction(nonExisten);
        trs = dao.getAllTransactions();
        trans.add(nonExisten);
        assertArrayEquals(trans.toArray(), trs.toArray());
        trans.remove(nonExisten);
        dao.removeTransaction(nonExisten);
        trs = dao.getAllTransactions();
        assertArrayEquals(trans.toArray(), trs.toArray());
    }
    @Ignore
    @Test
    public void testGetTransactionsBetweenDates() throws Exception {
        System.out.println("");
    }
    @Test(expected = Exception.class)
    public void testRemoveTransaction() throws Exception {
        System.out.println("removeTransaction");
        dao.removeTransaction(nonExisten);
        fail("failed");
    }
    @Test
    public void testEditTransaction() throws Exception {
        System.out.println("editTransaction");
        
        dao.addTransaction(nonExisten);
        trs = dao.getAllSales();
        assertTrue(trs.contains(nonExisten));
        trs = dao.getAllPurchases();
        assertFalse(trs.contains(nonExisten));
        nonExisten.setType(true);
        
        dao.editTransaction(nonExisten);
        trs = dao.getAllSales();
        assertFalse(trs.contains(nonExisten));
        trs = dao.getAllPurchases();
        assertTrue(trs.contains(nonExisten));
        
        dao.removeTransaction(nonExisten); 
        trs = dao.getAllSales();
        assertFalse(trs.contains(nonExisten));
        trs = dao.getAllPurchases();
        assertFalse(trs.contains(nonExisten));
        
        
    }
    
}
