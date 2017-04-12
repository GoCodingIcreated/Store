/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import dao.ProductDao;
import java.util.List;
import java.util.ArrayList;
import logic.Product;
import java.sql.SQLException;
import org.hibernate.Session;
import util.HibernateUtil;
import java.sql.Timestamp;
import logic.Customer;
/**
 *
 * @author nickolas
 */
public class ProductDaoImpl implements ProductDao
{
    public List<Product> getAllProduct() throws SQLException {
        List<Product> products;
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        products = session.createCriteria(Product.class).list();
        if (session != null && session.isOpen()) {
            session.close();  
        }
        return products;
    }
    public List<Product> getProductsByName(String name) throws SQLException {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products;
        products = session.createSQLQuery(
                            "SELECT * FROM product WHERE name = :NAME")
                            .addEntity(Product.class)
                            .setString("NAME", name)
                            .list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return products;
    }
    public List<Product> getProductsByType(String type) throws SQLException {
        List<Product> products;
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        products = session.createSQLQuery(
                            "SELECT * FROM product WHERE type = :TYPE")
                            .addEntity(Product.class)
                            .setString("TYPE", type)
                            .list();
        if (session != null && session.isOpen()) {
            session.close();
        }
        return products;
    }
    public List<Product> getProductsByDate(Timestamp date) throws SQLException {
        List<Product> products;
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        
        products = session.createSQLQuery(
                            "SELECT * FROM product WHERE timestore = :TIME")
                            .addEntity(Product.class)
                            .setString("TIME", date.toString())
                            .list();
        
        session.close();
        
        return products;
    }
    public void saveProduct(Product product) throws SQLException {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(product);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
    public void removeProduct(Product product) throws SQLException {
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(product);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        finally {
            
            session.close();
        }
    }
    public void editProduct(Product product) throws SQLException {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(product);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        finally {
            
            session.close();
        }
    }
    public List<Product> getProductsByPurcher(Customer customer) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products;
        products = session.createSQLQuery("SELECT p.id, p.name, p.timestore,"
                                + " p.type, p.about\n"
                                + "FROM Product P JOIN "
                                + " transaction T ON p.id = t.product_id JOIN "
                                + "customer C ON t.customer_id = c.id\n"
                                + "WHERE t.product_id = p.id and\n"
                                + "	t.customer_id = c.id and\n"
                                + "     t.type = true and "
                                + "     c.id = :ID")
                                .addEntity(Product.class)
                                .setLong("ID", customer.getId())
                                .list();        
        return products;
    }
    public List<Product> getProductsBySaler(Customer customer) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Product> products;
        products = session.createSQLQuery("SELECT p.id, p.name, p.timestore,"
                                + " p.type, p.about\n"
                                + "FROM Product P JOIN"
                                + " transaction T ON p.id = t.product_id JOIN "
                                + "customer C ON t.customer_id = c.id\n"
                                + "WHERE t.product_id = p.id and\n"
                                + "	t.customer_id = c.id and\n"
                                + "     t.type = false and"
                                + "     c.id = :ID")
                                .addEntity(Product.class)
                                .setLong("ID", customer.getId())
                                .list();  
        session.close();
        return products;
    }
    
}
