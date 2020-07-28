/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import nguyennst.cart.Cart;
import nguyennst.tblUser.TblUserDAO;
import nguyennst.utils.VerifyCapcha;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class LoginAction {
    
    private static final Logger logger = Logger.getLogger(LoginAction.class);
    
    private static final String EMPLOYEE = "employee";
    private static final String LEADER = "leader";
    private static final String MANAGER = "manager";
    private static final String FAIL = "fail";
    
    private String userID, password;
    
    
    
    public LoginAction() {
    }
    
    public String execute() {
        String url = FAIL;
        try {
            TblUserDAO dao = new TblUserDAO();
            int roleID = dao.checkLogin(userID, password);
            int count = 0;
            HttpServletRequest request = ServletActionContext.getRequest();
            String reCapcha = request.getParameter("g-recaptcha-response");
            if (!VerifyCapcha.verify(reCapcha)){
                request.setAttribute("INVALID", "Invalid reCapcha.");
                return FAIL;
            }
            if(roleID == 0){
                request.setAttribute("INVALID", "Invalid Email or Password.");
                url = FAIL;
            } else {
                HttpSession session = request.getSession();
                int statusID = dao.getStatusID();
                session.setAttribute("USERID", userID);
                session.setAttribute("NAME", dao.getName());
                if(roleID == 1 && statusID == 2){
                    session.setAttribute("ROLEID", roleID);
                    url = MANAGER;
                }
                if(roleID == 2 && statusID == 2){
                    session.setAttribute("ROLEID", roleID);
                    session.setAttribute("COUNT", count);
                    Cart cart = new Cart(dao.getName());
                    session.setAttribute("CART", cart);
                    url = LEADER;
                }
                if(roleID == 3 && statusID == 2){
                    session.setAttribute("ROLEID", roleID);
                    session.setAttribute("COUNT", count);
                    Cart cart = new Cart(dao.getName());
                    session.setAttribute("CART", cart);
                    url = EMPLOYEE;
                }
            }
        } catch (Exception e) {
            logger.error("LoginAction: " + e.getMessage());
        }
        return url;
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void validate(){
        
    }
}
