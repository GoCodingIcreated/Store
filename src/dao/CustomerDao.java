/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.SQLException;
import java.util.List;
import logic.Customer;
/**
 *
 * @author nickolas
 */
public interface CustomerDao
{
    public void temp();
    public List<Customer> getAllCustomers() throws SQLException;
    public List<Customer> getCustomersByName(String name) throws SQLException;
    public void saveCustomer(Customer customer) throws SQLException;
    public void removeCustomer(Customer customer) throws SQLException;
    public void editCustomer(Customer customer) throws SQLException;
}
