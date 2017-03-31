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
