/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblResource;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TblResourceDTO implements Serializable{
    private int resourceID;
    private String name, color;
    private int categoryID;
    private String categoryName;
    private int quantity;
    private int roleID;

    public TblResourceDTO() {
    }

    public TblResourceDTO(int resourceID, String name, String color, int quantity, int categoryID, int roleID) {
        this.resourceID = resourceID;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.roleID = roleID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }



    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the resourceID
     */
    public int getResourceID() {
        return resourceID;
    }

    /**
     * @param resourceID the resourceID to set
     */
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the roleID
     */
    public int getRoleID() {
        return roleID;
    }

    /**
     * @param roleID the roleID to set
     */
    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }


    
    
}
