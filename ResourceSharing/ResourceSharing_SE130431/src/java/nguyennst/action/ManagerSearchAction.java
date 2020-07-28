/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.tblBooking.TblBookingDAO;
import nguyennst.tblBooking.TblBookingDTO;
import nguyennst.tblDetail.TblDetailDAO;
import nguyennst.tblDetail.TblDetailDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class ManagerSearchAction {
    
    private static final Logger logger = Logger.getLogger(ManagerSearchAction.class);
    
    private static final String SUCCESS = "success";
    
    private String Sname;
    private String ScreateDate;
    private int SstatusID;
    private String SuserID;
    private int pageNumber;
    private List<TblDetailDTO> listRequest;
    private int numberOfPage;
    
    public ManagerSearchAction() {
    }
    
    public String execute() {
        String url = SUCCESS;
        try {
            String[] part = SuserID.split("@");
            String email = part[0];
            TblDetailDAO dao = new TblDetailDAO();
            TblBookingDAO bDAO = new TblBookingDAO();
            
            List<TblDetailDTO> listExpiry = dao.getRequestExpiry();
            for (TblDetailDTO dtoo : listExpiry) {
                dao.updateStatusRequest(dtoo.getDetailID(), 3);
            }
            
            int numberRecord = 20;
            int totalRecord = dao.adminGetNumberOfRequest(Sname, ScreateDate, SstatusID, email);
            numberOfPage = (totalRecord / numberRecord);
            if(totalRecord > numberOfPage * numberRecord){
                numberOfPage += 1;
            }
            int offset = (pageNumber - 1) * numberRecord;
            listRequest = dao.adminGetListRequest(offset, numberRecord, Sname, ScreateDate, SstatusID, email);
            for (TblDetailDTO dto : listRequest) {
                TblBookingDTO bDTO = bDAO.findElebyBookingID(dto.getBookingID());
                dto.setUserID(bDTO.getUserID());
                dto.setCreateDate(bDTO.getCreateDate());
            }
    
            
            
        } catch (NamingException ex) {
            logger.error("ManagerSearchAction_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("ManagerSearchAction_SQLE: " + ex.getMessage());
        } catch (ParseException ex) { 
            logger.error("ManagerSearchAction_Parse: " + ex.getMessage());
        }
        return url;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }

    public String getScreateDate() {
        return ScreateDate;
    }

    public void setScreateDate(String ScreateDate) {
        this.ScreateDate = ScreateDate;
    }

    public int getSstatusID() {
        return SstatusID;
    }

    public void setSstatusID(int SstatusID) {
        this.SstatusID = SstatusID;
    }

    public String getSuserID() {
        return SuserID;
    }

    public void setSuserID(String SuserID) {
        this.SuserID = SuserID;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<TblDetailDTO> getListRequest() {
        return listRequest;
    }

    public void setListRequest(List<TblDetailDTO> listRequest) {
        this.listRequest = listRequest;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    

   
    
    
}
