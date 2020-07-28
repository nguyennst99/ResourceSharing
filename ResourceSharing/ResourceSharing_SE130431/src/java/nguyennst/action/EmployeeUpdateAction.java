/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nguyennst.cart.Cart;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class EmployeeUpdateAction extends ActionSupport {
    
    private static final Logger logger = Logger.getLogger(EmployeeUpdateAction.class);
    
    private static final String SUCCESS = "success";
    
    @Override
    public void validate(){
        if(quantity.isEmpty()){
            addFieldError("quantity", "Quantity can't be blank!!!");
        }
    }
    
    private int detailID;
    private String quantity;
    
    public EmployeeUpdateAction() {
    }
    
    public String execute() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            cart.updateCart(detailID, Integer.parseInt(quantity));
            session.setAttribute("CART", cart);
        } catch (Exception ex) {
            logger.error("EmployeeUpdateAction: " + ex.getMessage());
        }
        return SUCCESS;
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
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    
}
