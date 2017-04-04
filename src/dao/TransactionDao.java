/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import dao.*;
import logic.*;
import java.util.List;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
/**
 *
 * @author nickolas
 */
public interface TransactionDao
{
    public List<Transaction> getAllTransactions() throws SQLException;
    public List<Transaction> 
        getTransactionsByCustomer(Customer customer) throws SQLException;
    public List<Transaction>
         getTransactionsByProduct(Product product) throws SQLException;
    public List<Transaction> getAllPurchases() throws SQLException;
    public List<Transaction> getAllSales() throws SQLException;
    public List<Transaction>
            getPurchasesByCustomer(Customer customer) throws SQLException;
    public List<Transaction>
            getSalesByCustomer(Customer customer) throws SQLException;
    public List<Transaction>
            getPurchasesByProduct(Product product) throws SQLException;
    public List<Transaction>
            getSalesByProduct(Product product) throws  SQLException;
    public void addTransaction(Transaction transaction) throws SQLException;
    public List<Transaction> getTransactionsBetweenDates(GregorianCalendar begin,
            GregorianCalendar end) throws SQLException;
    
}
