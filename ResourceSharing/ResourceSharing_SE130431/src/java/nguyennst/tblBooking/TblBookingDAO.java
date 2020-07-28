/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblBooking;

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
 * @author nguyennst
 */
public class TblBookingDAO implements Serializable {

    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean insertBooking(String userID) throws SQLException, NamingException {
        Date currentDate = new Date(System.currentTimeMillis());
        try {
            con = DBHelper.makeConnection();
            String url = "Insert Into tbl_Booking(userID, createDate) values (?,?)";
            stm = con.prepareStatement(url);
            stm.setString(1, userID);
            stm.setDate(2, currentDate);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int getLastBookingID() throws SQLException, NamingException {
        int bookingID = 1;
        try {
            con = DBHelper.makeConnection();
            String url = "Select MAX(bookingID) as BookingID From tbl_Booking";
            stm = con.prepareStatement(url);
            rs = stm.executeQuery();

            if (rs.next()) {
                bookingID = rs.getInt("BookingID");
            }
        } finally {
            closeConnection();
        }
        return bookingID;
    }
    
    public TblBookingDTO findElebyBookingID(int bookingID) throws NamingException, SQLException{
        TblBookingDTO dto = null;
        try{
            con = DBHelper.makeConnection();
            String url = "Select userID, createDate From tbl_Booking Where bookingID = ?";
            stm = con.prepareStatement(url);
            stm.setInt(1, bookingID);
            rs = stm.executeQuery();
            if(rs.next()){
                String userID = rs.getString("userID");
                String createDate = rs.getString("createDate");
                dto = new TblBookingDTO(userID, createDate);
            }
        } finally{
            closeConnection();
        }
        return dto;
    }
}
