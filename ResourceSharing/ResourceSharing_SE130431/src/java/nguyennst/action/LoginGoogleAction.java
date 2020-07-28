/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import com.opensymphony.xwork2.ActionContext;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import nguyennst.tblUser.TblUserDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class LoginGoogleAction {

    private static final Logger logger = Logger.getLogger(LoginGoogleAction.class);

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    public LoginGoogleAction() {
    }

    private String email;
    private String name;

    public String execute() {
        String url = FAIL;
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            if (email.isEmpty() || email.length() == 0) {
                request.setAttribute("INVALID", "Login Google failed.");
                url = FAIL;
            }
            TblUserDAO dao = new TblUserDAO();
            if (dao.checkEmail(email) == false) {
                dao.insertUserGoogle(email, name);
            }
            Map session = ActionContext.getContext().getSession();
            session.put("USERID", email);
            session.put("NAME", name);
            session.put("ROLEID", 3);
            session.put("COUNT", 0);
            url = SUCCESS;

        } catch (NamingException ex) {
            logger.error("LoginGoogleAction_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            logger.error("LoginGoogleAction_Naming: " + ex.getMessage());
        }
        return url;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
