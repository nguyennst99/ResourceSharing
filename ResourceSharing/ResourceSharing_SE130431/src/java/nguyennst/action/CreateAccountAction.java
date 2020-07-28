/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import nguyennst.cart.Cart;
import nguyennst.tblUser.TblUserDAO;
import nguyennst.utils.MailUtils;
import nguyennst.utils.RandomCode;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nguyennst
 */
public class CreateAccountAction extends ActionSupport {

    private static final Logger logger = Logger.getLogger(CreateAccountAction.class);

    private static final String CONFIRM_EMAIL = "confirm_email";
    private static final String FAIL = "fail";

    @Override
    public void validate() {
        // check email
        TblUserDAO dao = new TblUserDAO();
        try {
            if (getEmail().isEmpty()) {
                addFieldError("email", "Email can't be blank.");
            } else if (!email.matches("\\w+@\\w+[.]\\w+([.]\\w+)?") || getEmail().length() > 50) {
                addFieldError("email", "Email must be valid and <= 50 chars.");
            } else if (dao.checkEmail(getEmail()) == true) {
                addFieldError("email", "Email is existed.");
            }
        } catch (SQLException e) {
            logger.error("ERROR SQL at RegisterAction: " + e.getMessage());
        } catch (NamingException e) {
            logger.error("ERROR Naming at RegisterAction: " + e.getMessage());
        }

        // check password
        if (getPassword().isEmpty()) {
            addFieldError("password", "Password can't be blank.");
        } else if (!password.matches("^[a-zA-Z0-9]{5,20}$")) {
            addFieldError("password", "Password must be 5-20 characters and mustn't have space or special chars.");
        }

        // check confirm
        if (!confirm.matches(password)) {
            addFieldError("confirm", "Confirm must match password.");
        }

        // check name
        if (getName().isEmpty()) {
            addFieldError("name", "Name can't be blank.");
        } else if (getName().length() > 50) {
            addFieldError("name", "Name must be <= 50 chars.");
        }

        // check phone
        if (getPhone().isEmpty()) {
            addFieldError("phone", "Phone can't be blank.");
        } else if (getPhone().length() != 10) {
            addFieldError("phone", "Phone must be number with 10 digits.");
        }

        // check address
        if (getAddress().isEmpty()) {
            addFieldError("address", "Address can't be blank.");
        } else if (getAddress().length() > 50) {
            addFieldError("address", "Address must be <= 50 chars.");
        }
    }

    private String email, password, confirm, name, phone, address;

    public CreateAccountAction() {
    }

    public String execute() {
        String url = FAIL;
        try {
            TblUserDAO dao = new TblUserDAO();
            HttpServletRequest request = ServletActionContext.getRequest();
            Date date = new java.util.Date();
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
            String timeStr = spf.format(date);
            if (dao.insert(email, phone, name, address, timeStr, password)) {
                String code = RandomCode.getRandomCode();
                Map session = ActionContext.getContext().getSession();
                session.put("EMAIL", email);
                session.put("NAME", name);
                session.put("CODE", code);
                session.put("USERID", email);
                MailUtils.sendCodeToMemberMail(code, email);
                url = CONFIRM_EMAIL;
            } else {
                request.setAttribute("ERROR", "Create account failed.");
            }
        } catch (SQLException ex) {
            logger.error("CreateAccountAction_SQLE: " + ex.getMessage());
        } catch (NamingException ex) {
            logger.error("CreateAccountAction_Naming" + ex.getMessage());
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

    /**
     * @return the confirm
     */
    public String getConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(String confirm) {
        this.confirm = confirm;
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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

}
