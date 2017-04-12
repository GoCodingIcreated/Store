/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;
import dao.TransactionDao;
import java.sql.SQLException;
import java.util.List;
import logic.*;
import util.HibernateUtil;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
/**
 *
 * @author nickolas
 */
public class TransactionDaoImpl implements TransactionDao
{
    public List<Transaction> getAllTransactions() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran = session.createCriteria(Transaction.class).list();
        session.close();
        return tran;
    }
    public List<Transaction> getTransactionsByCustomer(Customer customer)
            throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE customer_id = :ID")
                                    .addEntity(Transaction.class)
                                    .setLong("ID", customer.getId())
                                    .list();
        session.close();
        return tran;
    }
    public List<Transaction> getTransactionsByProduct(Product product)
            throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE product_id = :ID")
                                    .addEntity(Transaction.class)
                                    .setLong("ID", product.getId())
                                    .list();
        session.close();
        return tran;
    }
    public List<Transaction> getAllPurchases() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE type = true")
                                    .addEntity(Transaction.class)
                                    .list();
        session.close();
        return tran;
    }
    public List<Transaction> getAllSales() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE type = false")
                                    .addEntity(Transaction.class)
                                    .list();
        session.close();
        return tran;
    }
    public List<Transaction> getPurchasesByCustomer(Customer customer)
            throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE customer_id = :ID AND "
                                    + "type = true")
                                    .addEntity(Transaction.class)
                                    .setLong("ID", customer.getId())
                                    .list();
        session.close();
        return tran;                
    }
    public List<Transaction> getSalesByCustomer(Customer customer)
            throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE customer_id = :ID AND "
                                    + "type = false")
                                    .addEntity(Transaction.class)
                                    .setLong("ID", customer.getId())
                                    .list();
        session.close();
        return tran;                
    }
    public List<Transaction> getPurchasesByProduct(Product product)
            throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE product_id = :ID AND "
                                    + "type = true")
                                    .addEntity(Transaction.class)
                                    .setLong("ID", product.getId())
                                    .list();
        session.close();
        return tran;
    }
    public List<Transaction> getSalesByProduct(Product product)
            throws  SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Transaction> tran;
        tran = session.createSQLQuery("SELECT * FROM transaction "
                                    + "WHERE product_id = :ID AND "
                                    + "type = false")
                                    .addEntity(Transaction.class)
                                    .setLong("ID", product.getId())
                                    .list();
        session.close();
        return tran;
    }
    public void addTransaction(Transaction transaction) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.save(transaction);
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
    public List<Transaction> getTransactionsBetweenDates(GregorianCalendar begin,
            GregorianCalendar end) throws SQLException {
        List<Transaction> tran;
        Session session = HibernateUtil.getSessionFactory().openSession();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("!" + df.format(begin.getTime()) + df.format(end.getTime()));
        tran = session.createSQLQuery("SELECT *\n" 
                    + "FROM transaction\n"
                    + "WHERE transaction.date >= :BEGIN AND\n"
                    + "	  transaction.date <= :END")
                .addEntity(Transaction.class)
                .setString("BEGIN", df.format(begin.getTime()))
                .setString("END", df.format(end.getTime()))
                .list();
        session.close();
        return tran;
    }
    public void removeTransaction(Transaction transaction) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.delete(transaction);
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
    public void editTransaction(Transaction transaction) throws SQLException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            session.update(transaction);
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
}
