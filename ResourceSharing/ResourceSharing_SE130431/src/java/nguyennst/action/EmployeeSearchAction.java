/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.tblCategory.TblCategoryDAO;
import nguyennst.tblResource.TblResourceDAO;
import nguyennst.tblResource.TblResourceDTO;
import nguyennst.utils.DateUtil;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class EmployeeSearchAction extends ActionSupport {
    
    private static Logger logger = Logger.getLogger(EmployeeSearchAction.class);
    
    private static final String SUCCESS = "success";
    @Override
    public void validate() {
        try {
            if (!DateUtil.checkDateAfter(rentalDate)) {
                addFieldError("returnDate", "Rental date can't be past.");
            } else if (!DateUtil.checkDateInOut(rentalDate, returnDate)) {
                addFieldError("returnDate", "Rental date & return date must be valid.");
            }
        } catch (Exception e) {
            logger.error("EmployeeSearchAction: " + e.getMessage());
        }
    }
    
    public EmployeeSearchAction() {
    }
    
    private String nameS;
    private int categoryIDS;
    private String rentalDate;
    private String returnDate;
    private int quantity;
    private int pageNumber;
    private List<TblResourceDTO> list;
    private int numberOfPage;
    private int roleID;
        
    public String execute() {
        String url = SUCCESS;
        try {
            TblCategoryDAO cateDAO = new TblCategoryDAO();
            TblResourceDAO rsDAO = new TblResourceDAO();
            int numberRecord = 20;
            int totalRecord = rsDAO.getNumberofResource(nameS, categoryIDS, rentalDate, returnDate);
            numberOfPage = (totalRecord / numberRecord);
            if(totalRecord > numberOfPage * numberRecord){
                numberOfPage +=  1;
            }
            int offset = (pageNumber - 1) * numberRecord;
            list = rsDAO.getListResource(nameS, categoryIDS, rentalDate, returnDate, offset, numberRecord);
            for (TblResourceDTO dto : list) {
                String categoryName = cateDAO.findCategoryNameByID(dto.getCategoryID());
                dto.setCategoryName(categoryName);
            }
        } catch (NamingException ex) {
            logger.error("EmployeeSearchAction_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("EmployeeSearchAction_SQLE: " + ex.getMessage());
        }
        return url;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public int getCategoryIDS() {
        return categoryIDS;
    }

    public void setCategoryIDS(int categoryIDS) {
        this.categoryIDS = categoryIDS;
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
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @return the list
     */
    public List<TblResourceDTO> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<TblResourceDTO> list) {
        this.list = list;
    }

    /**
     * @return the numberOfPage
     */
    public int getNumberOfPage() {
        return numberOfPage;
    }

    /**
     * @param numberOfPage the numberOfPage to set
     */
    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
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
