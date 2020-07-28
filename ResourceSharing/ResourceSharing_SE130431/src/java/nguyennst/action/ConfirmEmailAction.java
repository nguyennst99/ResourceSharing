/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import nguyennst.cart.Cart;
import nguyennst.tblUser.TblUserDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author nguyennst
 */
public class ConfirmEmailAction extends ActionSupport {

    private static final Logger logger = Logger.getLogger(ConfirmEmailAction.class);

    private static final String SUCCESS = "success";

    private String code;

    Map session = ActionContext.getContext().getSession();

    @Override
    public void validate() {
        String code1 = (String) session.get("CODE");
        if (code.isEmpty() || code.length() == 0) {
            addFieldError("code", "Code verify can't be blank!");
        } else if (!code.equals(code1)) {
            addFieldError("code", "Code verify is invalid ");
        }
    }

    public ConfirmEmailAction() {
    }

    public String execute() {
        String url = SUCCESS;
        try {
            TblUserDAO dao = new TblUserDAO();
            String email = (String) session.get("EMAIL");
            dao.activeEmail(email);
            session.put("ROLEID", 3);
            int count = 0;
            Cart cart = new Cart(dao.getName());
            session.put("CART", cart);
            session.put("COUNT", count);
            url = SUCCESS;
        } catch (NamingException ex) {
            logger.error("ConfirmEmailAction_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("ConfirmEmailAction_SQLE: " + ex.getMessage());
        }
        return url;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
