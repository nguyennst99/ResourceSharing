/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblBooking;

import java.io.Serializable;

/**
 *
 * @author nguyennst
 */
public class TblBookingDTO implements Serializable{
    private String userID;
    private String createDate;

    public TblBookingDTO() {
    }

    public TblBookingDTO(String userID, String createDate) {
        this.userID = userID;
        this.createDate = createDate;
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
