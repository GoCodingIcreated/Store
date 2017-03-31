/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package logic;

/**
 *
 * @author nickolas
 */
public class Transaction {
    private Long id;
    private Long productId;
    private Long customerId;
    private Boolean type;
    private Integer count;
    private String date;
    
    public Transaction() {
        
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long id) {
        this.productId = id; 
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long id) {
        this.customerId = id; 
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    public Integer getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public Boolean getType() {
        return type;
    }
    public void setType(Boolean type) {
        this.type = type;
    }
    
}
