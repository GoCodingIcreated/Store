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
    public Customer(Long id, String phone, String adres, String name) {
        this.id = id;
        this.phone = phone;
        this.adres = adres;
        this.name = name;
    }
    public Customer(Customer c) {
        id = c.id;
        phone = c.phone;
        adres = c.adres;
        name = c.name;
        transactions = c.transactions;
    }
    public boolean equals(Object obj) {
        Customer c = (Customer )obj;
        return c.id.equals(id) && name.equals(c.name) 
                && c.phone.equals(phone) && c.adres.equals(adres);
    }
    public int hashCode() {
        return (int)(long)id;
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
