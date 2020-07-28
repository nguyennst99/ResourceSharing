/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblUser;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nguyennst.utils.DBHelper;

/**
 *
 * @author Admin
 */
public class TblUserDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if(stm!=null){
            stm.close();
        }
        if(con!=null){
            con.close();
        }
    }
    
    private String name;

    public String getName() {
        return name;
    }
    
    private int statusID;

    public int getStatusID() {
        return statusID;
    }
    
    
    
    public int checkLogin(String userID, String password) throws NamingException, SQLException{
        int role = 0;
        try{
            con = DBHelper.makeConnection();
            String url = "Select roleID, name, statusID from tbl_User Where userID = ? and password = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, userID);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if(rs.next()){
                this.name = rs.getString("name");
                role = rs.getInt("roleID");
                this.statusID = rs.getInt("statusID");
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public boolean checkEmail(String email) throws NamingException, SQLException{
        try{
            con = DBHelper.makeConnection();
            String url = "Select userID From tbl_User Where userID = ?";
            stm = con.prepareStatement(url);
            stm.setString(1, email);
            rs = stm.executeQuery();
            if(rs.next()){
                return true;
            }
        } finally {
            closeConnection();;
        }
        return false;
    }
    
    public boolean insertUserGoogle(String userID, String name) throws NamingException, SQLException{
        try {
            con = DBHelper.makeConnection();
            String url = "Insert Into tbl_User(userID, name, statusID, roleID) values(?,?,?,?)";
            stm = con.prepareStatement(url);
            stm.setString(1, userID);
            stm.setString(2, name);
            stm.setInt(3, 2);
            stm.setInt(4, 3);
            int row = stm.executeUpdate();
            if(row > 0){
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
    public boolean insert(String email, String phone, String name, String address, String createTime, String password) throws SQLException, NamingException {
        try {
            con = DBHelper.makeConnection();
            String sql = "Insert Into tbl_User(userID, phone, name, address, createDate, password, roleID, statusID) values(?,?,?,?,?,?,?,?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, phone);
            stm.setString(3, name);
            stm.setString(4, address);
            stm.setString(5, createTime);
            stm.setString(6, password);
            stm.setInt(7, 3);
            stm.setInt(8, 1);
            
            int row = stm.executeUpdate();
            if(row > 0){
                return true;
            }
            
        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean activeEmail(String email) throws SQLException, NamingException {
        try {
            con = DBHelper.makeConnection();
            String sql = "Update tbl_User Set StatusID = 2 WHERE userID = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);

            int row = stm.executeUpdate();
            if(row > 0) {
                return  true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }
    
}
