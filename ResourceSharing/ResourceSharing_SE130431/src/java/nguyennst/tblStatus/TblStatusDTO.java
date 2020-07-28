/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblStatus;

import java.io.Serializable;

/**
 *
 * @author nguyennst
 */
public class TblStatusDTO implements Serializable{
    private int statusID;
    private String statusName;

    public TblStatusDTO() {
    }

    public TblStatusDTO(int statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
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
     * @return the statusName
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * @param statusName the statusName to set
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
    
}
