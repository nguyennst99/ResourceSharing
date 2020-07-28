/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblDetail;

import java.io.Serializable;

/**
 *
 * @author nguyennst
 */
public class TblDetailDTO implements Serializable{
    private int detailID, resourceID, quantity, statusID;
    private String name, rentalDate, returnDate;
    private String categoryName;
    private int bookingID;
    private String userID;
    private String createDate;

    public TblDetailDTO() {
    }

    public TblDetailDTO(int detailID, int resourceID, int quantity, String name, String rentalDate, String returnDate, String categoryName) {
        this.detailID = detailID;
        this.resourceID = resourceID;
        this.quantity = quantity;
        this.name = name;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.categoryName = categoryName;
    }

    public TblDetailDTO(int detailID, int resourceID, int quantity, int statusID, String name, String rentalDate, String returnDate, int bookingID) {
        this.detailID = detailID;
        this.resourceID = resourceID;
        this.quantity = quantity;
        this.statusID = statusID;
        this.name = name;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.bookingID = bookingID;
    }

    public TblDetailDTO(int detailID) {
        this.detailID = detailID;
    }

    
    /**
     * @return the detailID
     */
    public int getDetailID() {
        return detailID;
    }

    /**
     * @param detailID the detailID to set
     */
    public void setDetailID(int detailID) {
        this.detailID = detailID;
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
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the resourceName
     */
    public String getName() {
        return name;
    }

    /**
     * @param resourceName the resourceName to set
     */
    public void setName(String resourceName) {
        this.name = resourceName;
    }

    /**
     * @return the rentalDate
     */
    public String getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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
     * @return the bookingID
     */
    public int getBookingID() {
        return bookingID;
    }

    /**
     * @param bookingID the bookingID to set
     */
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    
}
