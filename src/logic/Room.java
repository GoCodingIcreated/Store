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
public class Room {
    private Long id;
    private Long storeId;
    private Integer number;
    private Integer capacity;
    private Set storePlaces = new HashSet();
    public Room() {
        
    }
    public Set getStorePlaces() {
        return storePlaces;
    }
    public void setStorePlaces(Set places) {
        storePlaces = places;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public Long getStoreId() {
        return storeId;
    }
    public void setStoreId(Long id) {
        this.storeId = id;
    }
    public Integer getNumber() {
        return number;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
