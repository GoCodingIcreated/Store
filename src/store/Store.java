/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package store;

/**
 *
 * @author nickolas
 */

import util.Factory;
import logic.*;
import java.util.List;
import java.sql.SQLException;
import util.HibernateUtil;
import java.sql.Timestamp;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Store {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        try {
            test1();
            test2();
            test3();
            test4();
            test5();
            test6();
            test7();
        }
        catch(Exception ex) {
            System.out.println("ERROR: " + ex);
        }
        finally {
            HibernateUtil.getSessionFactory().close();
        }
    }
    public static void test1() throws SQLException {
        System.out.println("Test1 started");
        Factory factory = Factory.getInstance();
        List<Product> products = factory.getProductDao().getAllProduct();
        
        for (Product product : products) {
            System.out.println(product.getName());
            System.out.println("================");
        }
        System.out.println("Test1 finished");
    }
    public static void test2() throws SQLException {
        System.out.println("Test2 started");
        Factory factory = Factory.getInstance();
        List<Product> products = factory.getProductDao()
                                    .getProductsByName("productname1");
        if (products == null) {
            System.out.println("Empty products list");
        }
        else {
            for (Product product : products) {
                System.out.println(product.getName());
                System.out.println("================");
            }
        }
        System.out.println("Test2 finished");
    }
    public static void test3() throws SQLException {
        System.out.println("Test3 started");
        Factory factory = Factory.getInstance();
        List<Product> products = factory.getProductDao()
                                    .getProductsByType("food");
        if (products == null) {
            System.out.println("Empty products list");
        }
        else {
            for (Product product : products) {
                System.out.println(product.getName());
                System.out.println("================");
            }
        }
        System.out.println("Test3 finished");        
    }
    public static void test4() throws SQLException {
        System.out.println("Test4 started");
        Factory factory = Factory.getInstance();
        Product product = new Product();
        for (int i = 0; i < 10; ++i) {
            product.setName(("newProduct:COUNT")
                            .replace(":COUNT", new Integer(i).toString()));
            product.setType("newType");
            product.setAbout("newAbout");
            product.setTimestore(new Timestamp(System.currentTimeMillis()));
            factory.getProductDao().saveProduct(product);
        }
        System.out.println("Test4 finished");
    }
    public static void test5() throws SQLException {
        System.out.println("Test5 started");
        Factory factory = Factory.getInstance();
        String name = "newProduct:COUNT";
        for (int i = 0; i < 10; ++i) {
            List<Product> products = factory.getProductDao()
                                .getProductsByName(name.replace(":COUNT"
                                        , new Integer(i).toString()));
            for (Product product : products) {
                System.out.println(product.getName());
                factory.getProductDao().removeProduct(product);
            }
        }
        System.out.println("Test5 finished");
    }
    public static void test6() throws SQLException {
        System.out.println("Test6 started");
        Factory factory = Factory.getInstance();
        String name = "oldCustomer", nameNew = "newCustomer";
        Customer customer = new Customer();
        customer.setName(name);
        customer.setAdres("adres");
        customer.setPhone("123123123");
        factory.getCustomerDao().saveCustomer(customer);
        List<Customer> customers = factory.getCustomerDao()//.getAllCustomers();
                                    .getCustomersByName(name);
        
        for (Customer cust : customers) {
            System.out.println(cust.getName() + ", " + cust.getId());
            cust.setName(nameNew);
            factory.getCustomerDao().editCustomer(cust);
        }
        
        
        System.out.println("==========");
        
        customers = factory.getCustomerDao().getCustomersByName(nameNew);
        for (Customer cust : customers) {
            System.out.println(cust.getName() + ", " + cust.getId());
            factory.getCustomerDao().removeCustomer(cust);
        }
        System.out.println("Test6 finished");
    }
    public static void test7() throws SQLException {
        System.out.println("Test7 started");
        GregorianCalendar begin = new GregorianCalendar(2017, 0, 27);
        GregorianCalendar end = new GregorianCalendar(2017, 1, 28);
        Factory factory = Factory.getInstance();
        List<Transaction> trans = factory.getTransactionDao()
                .getTransactionsBetweenDates(begin, end);
        if (trans == null) {
            System.out.println("Empty");
        }
        for (Transaction tran : trans) {
            System.out.println(tran.getDate() + ", " + tran.getId());
        }
        System.out.println("Test7 finished");
    }
}
