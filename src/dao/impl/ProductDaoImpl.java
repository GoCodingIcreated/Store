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
/**
 *
 * @author nickolas
 */
public class ProductDaoImpl implements ProductDao
{
    public List<Product> getAllProduct() throws SQLException {
        List<Product> products =  new ArrayList<Product>();
        Session session = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createCriteria(Product.class).list();
        }
        catch(Exception e) {
            System.out.println("Error: " + e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();  
            }
        }
        return products;
    }
    public List<Product> getProductsByName(String name) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createSQLQuery(
                                "SELECT * FROM product WHERE name = :NAME")
                                .addEntity(Product.class)
                                .setString("NAME", name)
                                .list();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }
    public List<Product> getProductsByType(String type) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createSQLQuery(
                                "SELECT * FROM product WHERE type = :TYPE")
                                .addEntity(Product.class)
                                .setString("TYPE", type)
                                .list();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }
    public List<Product> getProductsByDate(Timestamp date) throws SQLException {
        List<Product> products = new ArrayList<Product>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            products = session.createSQLQuery(
                                "SELECT * FROM product WHERE timestore = :TIME")
                                .addEntity(Product.class)
                                .setString("TIME", date.toString())
                                .list();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return products;
    }
    public Boolean saveProduct(Product product) throws SQLException {
        Session session = null;
        Boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            success = true;
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }
    public Boolean removeProduct(Product product) throws SQLException {
        Session session = null;
        Boolean success = false;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            success = true;
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return success;
    }
}
