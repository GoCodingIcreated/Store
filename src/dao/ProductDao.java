/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import logic.*;
import java.util.List;
import java.sql.SQLException;
import java.sql.Timestamp;
/**
 *
 * @author nickolas
 */
public interface ProductDao
{
    public List<Product> getAllProduct() throws SQLException;
    public List<Product> getProductsByName(String name) throws SQLException;
    public List<Product> getProductsByType(String type) throws SQLException;
    public List<Product> getProductsByDate(Timestamp date) throws SQLException;
    public List<Product> getProductsByPurcher(Customer customer) throws SQLException;
    public List<Product> getProductsBySaler(Customer customer) throws SQLException;
    
    public void saveProduct(Product product) throws SQLException;
    public void removeProduct(Product product) throws SQLException;
    public void editProduct(Product product) throws SQLException;
}
