/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import java.sql.SQLException;
import javax.naming.NamingException;
import nguyennst.tblDetail.TblDetailDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class ManagerDeleteAction {
    
     private static final Logger logger = Logger.getLogger(ManagerApprovalAction.class);
    
    private static final String SUCCESS = "success";
    
    private int detailID;
    
    
    
    public ManagerDeleteAction() {
    }
    
    public String execute(){
        String url = SUCCESS;
        try {
            TblDetailDAO dao = new TblDetailDAO();
            dao.managerUpdateRequest(detailID, 3);
        } catch (NamingException ex) {
            logger.error("ManagerDeleteAction_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("ManagerDeleteAction_SQLE: " + ex.getMessage());
        }
        return url;
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
    
}
