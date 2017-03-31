/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.util.Set;
import java.util.HashSet;
/**
 *
 * @author nickolas
 */
public class Customer {
    private Long id;
    private String phone;
    private String adres;
    private String name;
    private Set transactions = new HashSet();
    public Customer() {
        
    }
    public Set getTransactions() {
        return transactions;
    }
    public void setTransactions(Set transactions) {
        this.transactions = transactions;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAdres() {
        return adres;
    }
    public String getName() {
        return name;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
