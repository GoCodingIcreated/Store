/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.util.Set;
import java.util.HashSet;
import java.sql.Timestamp;
/**
 *
 * @author nickolas
 */
public class Product {
    private Long id;
    private String type;
    private Timestamp timestore;
    private String name;
    private String about;
    private Set transactions = new HashSet();
    private Set storePlaces = new HashSet();
    public Product() {
        
    }
    public Product(Product p) {
        id = p.id;
        type = p.type;
        timestore = p.timestore;
        name = p.name;
        about = p.about;
        transactions = p.transactions;
        storePlaces = p.storePlaces;
    }
    
    public Product(Long id, String name, Timestamp timestore,
            String type, String about) {
        this.id = id;
        this.type = type;
        this.timestore = timestore;
        this.name = name;
        this.about = about;
    }
    public boolean equals(Object obj) {
        if (obj.getClass() != Product.class) {
            return false;
        }
        Product c = (Product )obj;
        return c.id == id && name.equals(c.name) 
                && c.type.equals(type);
    }
    public int hashCode() {
        return (int)(long)id;
    }
    public Set getTransactions() {
        return transactions;
    }
    public Set getStorePlaces() {
        return storePlaces;
    }
    public void setTransactions(Set transactions) {
        this.transactions = transactions;
    }
    public void setStorePlaces(Set storePlaces) {
        this.storePlaces = storePlaces;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public String getType() {
        return type;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getAbout() {
        return about;
    }
    public String getName() {
        return name;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setTimestore(Timestamp time) {
        this.timestore = time;
    }
    public Timestamp getTimestore() {
        return timestore;
    }
}
