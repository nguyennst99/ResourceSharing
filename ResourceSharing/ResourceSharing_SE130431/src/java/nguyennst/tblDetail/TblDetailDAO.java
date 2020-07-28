/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.utils.DBHelper;

/**
 *
 * @author nguyennst
 */
public class TblDetailDAO implements Serializable {

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

    public int checkResource(int resourceID, String rentalDate, String returnDate) throws SQLException, NamingException {
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            String url = "Select DISTINCT resourceID From tbl_Detail "
                    + "	Where resourceID = ? and statusID = ? and"
                    + " ((RentalDate <= ? AND ReturnDate >= ?) OR (RentalDate <= ? AND ReturnDate >= ?))";
            stm = con.prepareStatement(url);
            stm.setInt(1, resourceID);
            stm.setInt(2, 2);
            stm.setString(3, rentalDate);
            stm.setString(4, rentalDate);
            stm.setString(5, returnDate);
            stm.setString(6, returnDate);
            rs = stm.executeQuery();

            if (rs.next()) {
                result = rs.getInt("resourceID");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int checkReousceRenting(int resourceID, String rentalDate, String returnDate, int quantity) throws SQLException, NamingException {
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            String url = "Select (c.quantity - (t.SumQuantity + ?)) as Total FROM tbl_Resource as c,(Select SUM(quantity) as SumQuantity"
                    + " From tbl_Detail "
                    + "	Where resourceID = ? and statusID = ? and ((RentalDate <= ? and ReturnDate >= ?) OR (RentalDate <= ? and ReturnDate >= ?))) as t"
                    + " WHERE c.resourceID = ?";
            stm = con.prepareStatement(url);
            stm.setInt(1, quantity);
            stm.setInt(2, resourceID);
            stm.setInt(3, 2);
            stm.setString(4, rentalDate);
            stm.setString(5, rentalDate);
            stm.setString(6, returnDate);
            stm.setString(7, returnDate);
            stm.setInt(8, resourceID);
            rs = stm.executeQuery();

            if (rs.next()) {
                result = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int checkQuantityOfResource(int resourceID, int quantity) throws SQLException, NamingException {
        int result = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "Select (quantity - ?) as Total From tbl_Resource Where resourceID = ?";
            stm = con.prepareStatement(sql);
            stm.setInt(1, quantity);
            stm.setInt(2, resourceID);
            rs = stm.executeQuery();

            if (rs.next()) {
                result = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(TblDetailDTO dto, int bookingID) throws SQLException, NamingException {

        try {
            con = DBHelper.makeConnection();
            String url = "Insert Into tbl_Detail (resourceName, bookingID, resourceID, quantity, RentalDate, ReturnDate, statusID)"
                    + " values (?,?,?,?,?,?,?)";
            stm = con.prepareStatement(url);
            stm.setString(1, dto.getName());
            stm.setInt(2, bookingID);
            stm.setInt(3, dto.getResourceID());
            stm.setInt(4, dto.getQuantity());
            stm.setString(5, dto.getRentalDate());
            stm.setString(6, dto.getReturnDate());
            stm.setInt(7, 1);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int adminGetNumberOfRequest(String name, String date, int status, String email) throws NamingException, SQLException, ParseException {
        int number = 0;
        try {
            con = DBHelper.makeConnection();
            if (!email.isEmpty() || email.length() != 0) {
                String url = "Select COUNT(c.bookingID) as Total from tbl_Detail as c LEFT JOIN "
                        + " (Select bookingID from tbl_Booking  GROUP BY bookingID) "
                        + " as t ON c.bookingID = t.bookingID "
                        + " Where c.resourceName LIKE ? and "
                        + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                        + " (Select bookingID from tbl_Booking where userID LIKE ? ";
                if (status != 0) {
                    url += " And statusID = " + status + " ";
                }
                if (!date.isEmpty() || date.length() != 0) {
                    url += " And createDate = '" + date + "' ";
                }
                url += " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, "%" + email + "%");
            } else {
                if (status != 0) {
                    String url = "Select COUNT(c.bookingID) as Total from tbl_Detail as c LEFT JOIN "
                            + " (Select bookingID from tbl_Booking  GROUP BY bookingID) "
                            + " as t ON c.bookingID = t.bookingID "
                            + " Where c.resourceName LIKE ? and "
                            + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                            + " (Select bookingID from tbl_Booking where statusID = ? ";
                    if (!date.isEmpty() || date.length() != 0) {
                        url += " And createDate = '" + date + "' ";
                    }
                    url += " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                    stm = con.prepareStatement(url);
                    stm.setString(1, "%" + name + "%");
                    stm.setInt(2, status);
                } else {
                    if (!date.isEmpty() || date.length() != 0) {
                        String url = "Select COUNT(c.bookingID) as Total from tbl_Detail as c LEFT JOIN "
                                + " (Select bookingID from tbl_Booking  GROUP BY bookingID) "
                                + " as t ON c.bookingID = t.bookingID "
                                + " Where c.resourceName LIKE ? and "
                                + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                                + " (Select bookingID from tbl_Booking where createDate = ? ";
                        url += "  GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                        stm = con.prepareStatement(url);
                        stm.setString(1, "%" + name + "%");
                        stm.setString(2, date);
                    } else {
                        String url = "Select COUNT(c.bookingID) as Total from tbl_Detail as c LEFT JOIN "
                                + " (Select bookingID from tbl_Booking  GROUP BY bookingID) "
                                + " as t ON c.bookingID = t.bookingID "
                                + " Where c.resourceName LIKE ? and "
                                + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                                + " (Select bookingID from tbl_Booking ";
                        url += "  GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                        stm = con.prepareStatement(url);
                        stm.setString(1, "%" + name + "%");
                    }
                }
            }
            rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return number;
    }

    public List<TblDetailDTO> adminGetListRequest(int offset, int next, String name, String date, int status, String email)
            throws NamingException, SQLException, ParseException {
        List<TblDetailDTO> listRequest = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();

            if (!email.isEmpty() || email.length() != 0) {
                String url = "Select c.detailID, c.resourceName, c.bookingID, c.resourceID, c.quantity, c.RentalDate, c.ReturnDate, c.statusID "
                        + " From tbl_Detail as c LEFT JOIN "
                        + " (Select bookingID From tbl_Booking GROUP BY bookingID) "
                        + " as t ON c.bookingID = t.bookingID "
                        + " Where c.resourceName LIKE ? and "
                        + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                        + " (Select bookingID from tbl_Booking where userID LIKE ? ";

                if (status != 0) {
                    url += " And statusID = " + status + " ";
                }
                if (!date.isEmpty() || date.length() != 0) {
                    url += " And createDate = '" + date + "' ";
                }
                url += " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                url += " ORDER BY c.bookingID DESC"
                        + " OFFSET ? ROWS"
                        + " FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, "%" + email + "%");
                stm.setInt(3, offset);
                stm.setInt(4, next);
            } else {
                if (status != 0) {
                    String url = "Select c.detailID, c.resourceName, c.bookingID, c.resourceID, c.quantity, c.RentalDate, c.ReturnDate, c.statusID "
                            + " From tbl_Detail as c LEFT JOIN "
                            + " (Select bookingID From tbl_Booking GROUP BY bookingID) "
                            + " as t ON c.bookingID = t.bookingID "
                            + " Where c.resourceName LIKE ? and "
                            + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                            + " (Select bookingID from tbl_Booking where statusID = ? ";
                    if (!date.isEmpty() || date.length() != 0) {
                        url += " And createDate = '" + date + "' ";
                    }
                    url += "  GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                    url += " ORDER BY c.bookingID DESC"
                            + " OFFSET ? ROWS"
                            + " FETCH NEXT ? ROWS ONLY";
                    stm = con.prepareStatement(url);
                    stm.setString(1, "%" + name + "%");
                    stm.setInt(2, status);
                    stm.setInt(3, offset);
                    stm.setInt(4, next);
                } else {
                    if (!date.isEmpty() || date.length() != 0) {
                        String url = "Select c.detailID, c.resourceName, c.bookingID, c.resourceID, c.quantity, c.RentalDate, c.ReturnDate, c.statusID "
                                + " From tbl_Detail as c LEFT JOIN "
                                + " (Select bookingID From tbl_Booking GROUP BY bookingID) "
                                + " as t ON c.bookingID = t.bookingID "
                                + " Where c.resourceName LIKE ? and "
                                + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                                + " (Select bookingID from tbl_Booking where createDate = ? ";
                        url += " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                        url += " ORDER BY c.bookingID DESC"
                                + " OFFSET ? ROWS"
                                + " FETCH NEXT ? ROWS ONLY";
                        stm = con.prepareStatement(url);
                        stm.setString(1, "%" + name + "%");
                        stm.setString(2, date);
                        stm.setInt(3, offset);
                        stm.setInt(4, next);
                    } else {
                        String url = "Select c.detailID, c.resourceName, c.bookingID, c.resourceID, c.quantity, c.RentalDate, c.ReturnDate, c.statusID "
                                + " From tbl_Detail as c LEFT JOIN "
                                + " (Select bookingID From tbl_Booking GROUP BY bookingID) "
                                + " as t ON c.bookingID = t.bookingID "
                                + " Where c.resourceName LIKE ? and "
                                + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                                + " (Select bookingID from tbl_Booking";
                        url += " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                        url += " ORDER BY c.bookingID DESC"
                                + " OFFSET ? ROWS "
                                + " FETCH NEXT ? ROWS ONLY";
                        stm = con.prepareStatement(url);
                        stm.setString(1, "%" + name + "%");
                        stm.setInt(2, offset);
                        stm.setInt(3, next);
                    }
                }
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                int detailID = rs.getInt("detailID");
                String resourceName = rs.getString("resourceName");
                int bookingID = rs.getInt("bookingID");
                int resourceID = rs.getInt("resourceID");
                int quantity = rs.getInt("quantity");
                String RentalDate = rs.getString("RentalDate");
                String ReturnDate = rs.getString("ReturnDate");
                int statusID = rs.getInt("statusID");

                TblDetailDTO dto = new TblDetailDTO(detailID, resourceID, quantity, statusID, resourceName, RentalDate, ReturnDate, bookingID);
                listRequest.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRequest;
    }

    public boolean managerUpdateRequest(int detailID, int statusID) throws NamingException, SQLException {
        try {
            con = DBHelper.makeConnection();
            String url = "Update tbl_Detail Set statusID = ? Where detailID = ?";
            stm = con.prepareStatement(url);
            stm.setInt(1, statusID);
            stm.setInt(2, detailID);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public int userGetHistory(String name, String date, String email) throws NamingException, SQLException {
        int number = 0;
        try {
            con = DBHelper.makeConnection();

            if (date.isEmpty() || date.length() == 0) {
                String url = "Select COUNT(c.bookingID) as Total from tbl_Detail as c LEFT JOIN "
                        + " (Select bookingID from tbl_Booking  GROUP BY bookingID) "
                        + " as t ON c.bookingID = t.bookingID "
                        + " Where c.resourceName LIKE ? and "
                        + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                        + " (Select bookingID from tbl_Booking where userID = ? "
                        + " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, email);

            } else {
                String url = "Select COUNT(c.bookingID) as Total from tbl_Detail as c LEFT JOIN "
                        + " (Select bookingID from tbl_Booking  GROUP BY bookingID) "
                        + " as t ON c.bookingID = t.bookingID "
                        + " Where c.resourceName LIKE ? and  "
                        + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                        + " (Select bookingID from tbl_Booking where createDate = ? and userID = ? "
                        + " GROUP BY bookingID) as t where c.bookingID = t.bookingID) ";
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, date);
                stm.setString(3, email);
            }
            rs = stm.executeQuery();
            if (rs.next()) {
                number = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return number;
    }

    public List<TblDetailDTO> userGetListHistory(int offset, int next, String name, String date, String email)
            throws NamingException, SQLException {
        List<TblDetailDTO> listRequest = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();

            if (date.isEmpty() || date.length() == 0) {
                String url = "Select c.detailID, c.resourceName, c.bookingID, c.resourceID, c.quantity, c.RentalDate, c.ReturnDate, c.statusID "
                        + " From tbl_Detail as c LEFT JOIN "
                        + " (Select bookingID From tbl_Booking GROUP BY bookingID) "
                        + " as t ON c.bookingID = t.bookingID "
                        + " Where c.resourceName LIKE ? and "
                        + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                        + " (Select bookingID from tbl_Booking where userID = ?"
                        + " GROUP BY bookingID) as t where c.bookingID = t.bookingID)"
                        + " ORDER BY c.bookingID DESC "
                        + " OFFSET ? ROWS "
                        + " FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, email);
                stm.setInt(3, offset);
                stm.setInt(4, next);

            } else {
                String url = "Select c.detailID, c.resourceName, c.bookingID, c.resourceID, c.quantity, c.RentalDate, c.ReturnDate, c.statusID "
                        + " From tbl_Detail as c LEFT JOIN "
                        + " (Select bookingID From tbl_Booking GROUP BY bookingID) "
                        + " as t ON c.bookingID = t.bookingID "
                        + " Where c.resourceName LIKE ? and "
                        + " c.bookingID IN (Select c.bookingID from tbl_Detail as c, "
                        + " (Select bookingID from tbl_Booking where createDate = ? and userID = ?"
                        + " GROUP BY bookingID) as t where c.bookingID = t.bookingID)"
                        + " ORDER BY c.bookingID DESC "
                        + " OFFSET ? ROWS"
                        + " FETCH NEXT ? ROWS ONLY";
                stm = con.prepareStatement(url);
                stm.setString(1, "%" + name + "%");
                stm.setString(2, date);
                stm.setString(3, email);
                stm.setInt(4, offset);
                stm.setInt(5, next);
            }
            rs = stm.executeQuery();
            while (rs.next()) {
                int detailID = rs.getInt("detailID");
                String resourceName = rs.getString("resourceName");
                int bookingID = rs.getInt("bookingID");
                int resourceID = rs.getInt("resourceID");
                int quantity = rs.getInt("quantity");
                String RentalDate = rs.getString("RentalDate");
                String ReturnDate = rs.getString("ReturnDate");
                int statusID = rs.getInt("statusID");

                TblDetailDTO dto = new TblDetailDTO(detailID, resourceID, quantity, statusID, resourceName, RentalDate, ReturnDate, bookingID);
                listRequest.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRequest;
    }
    
    public List<TblDetailDTO> getRequestExpiry() throws NamingException, SQLException{
        List<TblDetailDTO> listRequest = new ArrayList<>();
        Date currentDate = new Date(System.currentTimeMillis());
        try {
            con = DBHelper.makeConnection();
            String url = "Select detailID from tbl_Detail Where RentalDate < ? and statusID = 1";
            stm = con.prepareStatement(url);
            stm.setDate(1, currentDate);
            rs = stm.executeQuery();
            while(rs.next()){
                int detailID = rs.getInt("detailID");
                TblDetailDTO dto = new TblDetailDTO(detailID);
                listRequest.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRequest;
    }
    
    public boolean updateStatusRequest(int detailID, int status) throws NamingException, SQLException{
        try {
            con = DBHelper.makeConnection();
            String url = "Update tbl_Detail Set statusID = ? Where detailID = ?";
            stm = con.prepareStatement(url);
            stm.setInt(1, status);
            stm.setInt(2, detailID);
            int row = stm.executeUpdate();
            if (row > 0){
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

}
