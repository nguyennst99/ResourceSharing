/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.cart;

import java.io.Serializable;
import java.util.HashMap;
import nguyennst.tblDetail.TblDetailDTO;



/**
 *
 * @author Admin
 */
public class Cart implements Serializable{
    
    private String username;
    private HashMap<Integer, TblDetailDTO> items;

    public Cart(String username) {
        this.username = username;
        this.items = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<Integer, TblDetailDTO> getItems() {
        return items;
    }

    public void addToCart(TblDetailDTO dto) {
        this.items.put(dto.getDetailID(), dto);
    }

    public void removeCart(int detailID) {
        if (this.items.containsKey(detailID)) {
            this.items.remove(detailID);
        }
    }

    public void updateCart(int detailID, int quantity) {
        if (this.items.containsKey(detailID)) {
            this.items.get(detailID).setQuantity(quantity);
        }
    }

    
    
}
