/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import dao.CustomerDao;
import java.sql.SQLException;
import logic.Customer;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import util.HibernateUtil;
/**
 *
 * @author nickolas
 */
public class CustomerDaoImpl implements CustomerDao
{
    public void temp() {
        System.out.println("Hello, world!");
    }
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers;
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        customers = session.createCriteria(Customer.class).list();
        session.close();
        return customers;
    }
    public List<Customer> getCustomersByName(String name) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Customer> customers;
        customers = session.createSQLQuery(
                "SELECT * FROM Customer WHERE name = :NAME")
                .addEntity(Customer.class)
                .setString("NAME", name)
                .list();
        
        session.close();
        return customers;
    }
    public void saveCustomer(Customer customer) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }
    public void removeCustomer(Customer customer) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(customer);
        }
        catch (Exception e) {
            throw  e;
        }
        finally {
            session.getTransaction().commit();
            session.close();
        }
    }
    public void editCustomer(Customer customer) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(customer);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            throw e;
        }
        finally {
         
            session.close();
        }
    }
}
