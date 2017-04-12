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
public class Store {
    private Long id;
    private Set rooms = new HashSet();
    private String adres;

    public Store() {
        
    }
    public Store(Long id, String adres) {
        this.id = id;
        this.adres = adres;
    }
    public int hashCode() {
        return (int)(long)id;
    }
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Store.class) {
            return false;
        }
        Store st = (Store)obj;
        return id.equals(st.id) && adres.equals(st.adres);
    }
    public Set getRooms() {
        return rooms;
    }
    public void setRooms(Set rooms) {
        this.rooms = rooms;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id; 
    }
    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
    }

}
