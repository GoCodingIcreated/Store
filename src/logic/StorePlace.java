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
public class StorePlace {
    private Long id;
    private Long roomId;
    private Long productId;
    private Integer count;
    private Timestamp timeArrived;
    public StorePlace() {
        
    }
    public StorePlace(Long id, Long roomId, Long productId, int count) {
        this.id = id;
        this.roomId = roomId;
        this.productId = productId;
        this.count = count;
        this.timeArrived = new Timestamp(System.currentTimeMillis());
    }
    public boolean equals(Object obj) {
        if (obj.getClass() != StorePlace.class) {
            return false;
        }
        StorePlace sp = (StorePlace) obj;
        return sp.id.equals(id) && sp.count.equals(count)
                && sp.roomId.equals(roomId)
                && productId.equals(sp.productId);
    }
    public int hashCode() {
        return (int)(long)id;
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
    
    public Timestamp getTimeArrived() {
        return timeArrived;
    }
    public void setTimeArrived(Timestamp time) {
        this.timeArrived = time;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
}
