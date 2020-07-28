/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class LogoutAction {
    
    private static final Logger logger = Logger.getLogger(LoginAction.class);
    
    private static final String SUCCESS = "success";
    
    public LogoutAction() {
    }
    
    public String execute() {
        try {
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

        } catch (Exception e) {
            logger.error("LogoutAction: " + e.getMessage());
        }

        return SUCCESS;
    }
    
}
