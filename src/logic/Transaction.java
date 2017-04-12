/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package logic;
import java.sql.Timestamp;

/**
 *
 * @author nickolas
 */
public class Transaction {
    private Long id;
    private Long productId;
    private Long customerId;
    private Boolean type;
    private Double count;
    private Timestamp date;
    
    public Transaction() {
        
    }
    public Transaction(Long id, Long productId, Long customerId, Boolean type, Double count) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
        this.type = type;
        this.count = count;
        date = new Timestamp(System.currentTimeMillis());
        
    }
    public int hashCode() {
        return (int)(long)id;
    }
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Transaction.class) {
            return false;
        }
        Transaction tr = (Transaction)obj;
        return id.equals(tr.id) && productId.equals(tr.productId) &&
                customerId.equals(tr.customerId) && type.equals(tr.type) &&
                count.equals(tr.count);
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
    public void setDate(Timestamp date) {
        this.date = date;
    }
    public Timestamp getDate() {
        return date;
    }

    public Double getCount() {
        return count;
    }
    public void setCount(Double count) {
        this.count = count;
    }
    public Boolean getType() {
        return type;
    }
    public void setType(Boolean type) {
        this.type = type;
    }
    
}
