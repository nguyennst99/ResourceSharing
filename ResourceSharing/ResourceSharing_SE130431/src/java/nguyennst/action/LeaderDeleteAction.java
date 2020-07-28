/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nguyennst.cart.Cart;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class LeaderDeleteAction {

    private static final Logger logger = Logger.getLogger(LeaderDeleteAction.class);

    private static final String SUCCESS = "success";

    private int detailID;

    public LeaderDeleteAction() {
    }

    public String execute() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            cart.removeCart(detailID);
            session.setAttribute("CART", cart);
        } catch (Exception e) {
            logger.error("LeaderDeleteAction: " + e.getMessage());
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

}
