/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nguyennst.tblBooking.TblBookingDAO;
import nguyennst.tblBooking.TblBookingDTO;
import nguyennst.tblDetail.TblDetailDAO;
import nguyennst.tblDetail.TblDetailDTO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class EmployeeSearchHistoryAction {
    
    private static final Logger logger = Logger.getLogger(EmployeeSearchHistoryAction.class);
    
    private static final String SUCCESS = "success";
    
    private String nameR;
    private String date;
    private int pageNumber;
    private List<TblDetailDTO> listHistory;
    private int numberOfPage;
    
    public EmployeeSearchHistoryAction() {
    }
    
    public String execute() {
        String url = SUCCESS;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            String userID = (String) session.getAttribute("USERID");
            TblDetailDAO dao = new TblDetailDAO();
            TblBookingDAO bDAO = new TblBookingDAO();
            int numberRecord = 20;
            int totalRecord = dao.userGetHistory(nameR, date, userID);
            numberOfPage = (totalRecord / numberRecord);
            if (totalRecord > numberOfPage * numberRecord) {
                numberOfPage += 1;
            }
            int offset = (pageNumber - 1) * numberRecord;
            listHistory = dao.userGetListHistory(offset, numberRecord, nameR, date, userID);
            for (TblDetailDTO dto : listHistory) {
                TblBookingDTO bDTO = bDAO.findElebyBookingID(dto.getBookingID());
                dto.setUserID(bDTO.getUserID());
                dto.setCreateDate(bDTO.getCreateDate());
                
            }

        } catch (NamingException ex) {
            logger.error("EmployeeSearchHistoryAction_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("EmployeeSearchHistoryAction_SQLE: " + ex.getMessage());
        } 
        return url;
    }

    public String getNameR() {
        return nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }    

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<TblDetailDTO> getListHistory() {
        return listHistory;
    }

    public void setListHistory(List<TblDetailDTO> listHistory) {
        this.listHistory = listHistory;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }
}
