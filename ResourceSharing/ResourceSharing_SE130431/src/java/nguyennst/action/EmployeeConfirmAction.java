/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nguyennst.cart.Cart;
import nguyennst.tblBooking.TblBookingDAO;
import nguyennst.tblDetail.TblDetailDAO;
import nguyennst.tblDetail.TblDetailDTO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class EmployeeConfirmAction {

    private static final Logger logger = Logger.getLogger(EmployeeConfirmAction.class);

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public EmployeeConfirmAction() {
    }

    public String execute() {
        String url = FAIL;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            String email = (String) session.getAttribute("USERID");
            String name = (String) session.getAttribute("NAME");

            if (!cart.getItems().isEmpty()) {
                TblDetailDAO dDAO = new TblDetailDAO();
                TblBookingDAO bDAO = new TblBookingDAO();
                boolean valid = true;

                List<TblDetailDTO> listSoldOut = new ArrayList<>();

                for (TblDetailDTO dto : cart.getItems().values()) {
                    if (dDAO.checkResource(dto.getResourceID(), dto.getRentalDate(), dto.getReturnDate())== 0) {
                        if (dDAO.checkQuantityOfResource(dto.getResourceID(), dto.getQuantity()) < 0) {
                            valid = false;
                            listSoldOut.add(dto);
                            url = FAIL;
                        }
                    } else  
                    if (dDAO.checkReousceRenting(dto.getResourceID(), dto.getRentalDate(), dto.getReturnDate(), dto.getQuantity()) < 0) {
                        valid = false;
                        listSoldOut.add(dto);
                        url = FAIL;
                    }
                }

                if (valid) {
                    if (bDAO.insertBooking(email)) {
                        int bookingID = bDAO.getLastBookingID();
                        for (TblDetailDTO dto : cart.getItems().values()) {
                            dDAO.insert(dto, bookingID);
                        }
                        Cart newCart = new Cart(name);
                        session.setAttribute("CART", newCart);
                        session.setAttribute("COUNT", 0);
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("LISTSOLDOUT", listSoldOut);
                }
            }

        } catch (SQLException ex) {
            logger.error("EmployeeConfirmAction_SQLE: " + ex.getMessage());
        } catch (NamingException ex) {
            logger.error("EmployeeConfirmAction_Naming: " + ex.getMessage());
        }
        return url;
    }

}
