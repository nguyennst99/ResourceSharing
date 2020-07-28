/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nguyennst.cart.Cart;
import nguyennst.tblDetail.TblDetailDTO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class EmployeeRentAction {

    private static final Logger logger = Logger.getLogger(EmployeeRentAction.class);
    
    private static final String SUCCESS = "success";
    
    private int resourceID;
    private String name, categoryName, rentalDate, returnDate;
    private int quantity;

    public EmployeeRentAction() {
    }

    public String execute() {
        try {
            if (name != null && categoryName != null && rentalDate != null && returnDate != null) {
                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession();
                Cart cart = (Cart) session.getAttribute("CART");

                int count = (int) session.getAttribute("COUNT");
                count++;
                boolean valid = true;
                TblDetailDTO dto = new TblDetailDTO(count, resourceID, 1, name, rentalDate, returnDate, categoryName);

                for (TblDetailDTO dtoDetail : cart.getItems().values()) {
                    if (dto.getResourceID() == dtoDetail.getResourceID() && dto.getRentalDate().equals(dtoDetail.getRentalDate()) && dto.getReturnDate().equals(dtoDetail.getReturnDate())) {
                        valid = false;
                        cart.updateCart(dtoDetail.getDetailID(), dtoDetail.getQuantity() + 1);
                    }
                }
                if (valid) {
                    cart.addToCart(dto);                  
                }
                session.setAttribute("CART", cart);
                session.setAttribute("COUNT", count);
            }
        } catch (Exception e) {
            logger.error("EmployeeRentAction: " + e.getMessage());
        }
        return SUCCESS;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
