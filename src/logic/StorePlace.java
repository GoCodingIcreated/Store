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
public class StorePlace {
    private Long id;
    private Long roomId;
    private Long productId;
    private Integer count;
    private String timeArrived;
    public StorePlace() {
        
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
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long id) {
        this.roomId = id; 
    }
    
    public String getTimeArrived() {
        return timeArrived;
    }
    public void setTimeArrived(String time) {
        this.timeArrived = time;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}
