/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblResource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.utils.DBHelper;

/**
 *
 * @author Admin
 */
public class TblResourceDAO implements Serializable{
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
    
    public int getNumberofResource(String name, int categoryID, String RentalDate, String ReturnDate) throws NamingException, SQLException {
        int number = 0;
        try {
            con = DBHelper.makeConnection();
            String url = "Select COUNT(c.resourceID) as Total From tbl_Resource as c"
                    + " LEFT JOIN (Select resourceID, SUM(quantity) as Amount From tbl_Detail"
                    + "	Where ((RentalDate <= ? and ReturnDate >= ?) OR (RentalDate <= ? and ReturnDate >= ?)) and statusID = 2"
                    + "	GROUP BY resourceID) as t ON c.resourceID = t.resourceID"
                    + "	Where c.resourceID NOT IN (Select c.resourceID From tbl_Resource as c,"
                    + " (Select resourceID, SUM(quantity) as Amount From tbl_Detail"
                    + "	Where ((RentalDate <= ? and ReturnDate >= ?) OR (RentalDate <= ? and ReturnDate >= ?)) and statusID = 2"
                    + "	GROUP BY resourceID) as t"
                    + "	Where c.resourceID = t.resourceID and (c.quantity - t.Amount) <= 0)"
                    + "	and c.name LIKE ?";
            if (categoryID != 0) {
                url += " and categoryID = " + categoryID + "\n";
            }

            url += " and c.statusID = 2";
            stm = con.prepareStatement(url);
            stm.setString(1, RentalDate);
            stm.setString(2, RentalDate);
            stm.setString(3, ReturnDate);
            stm.setString(4, ReturnDate);
            stm.setString(5, RentalDate);
            stm.setString(6, RentalDate);
            stm.setString(7, ReturnDate);
            stm.setString(8, ReturnDate);
            stm.setString(9, "%" + name + "%");

            rs = stm.executeQuery();

            if (rs.next()) {
                number = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return number;
    }

    public List<TblResourceDTO> getListResource(String name, int categoryID, String RentalDate, String ReturnDate, int offset, int next)
            throws NamingException, SQLException {
        List<TblResourceDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            String url = "Select c.resourceID, c.name, c.color, c.quantity, c.categoryID, c.roleID From tbl_Resource as c"
                    + " LEFT JOIN (SELECT resourceID, SUM(quantity) as Amount FROM tbl_Detail"
                    + "	Where ((RentalDate <= ? and ReturnDate >= ?) OR (RentalDate <= ? and ReturnDate >= ?)) and statusID = 2"
                    + "	GROUP BY resourceID) as t ON c.resourceID = t.resourceID"
                    + "	Where c.resourceID NOT IN (SELECT c.resourceID FROM tbl_Resource as c,"
                    + " (SELECT resourceID, SUM(quantity) as Amount FROM tbl_Detail"
                    + "	Where ((RentalDate <= ? and ReturnDate >= ?) OR (RentalDate <= ? and ReturnDate >= ?)) and statusID = 2"
                    + "	GROUP BY resourceID) as t"
                    + "	Where c.resourceID = t.resourceID and (c.quantity - t.Amount) <= 0)"
                    + "	and c.name LIKE ?";
            if (categoryID != 0) {
                url += " and categoryID = " + categoryID;
            }
            url += " and c.statusID = 2"
                    + "	ORDER BY c.resourceID"
                    + "	OFFSET ? ROWS"
                    + "	FETCH NEXT ? ROWS ONLY";
            stm = con.prepareStatement(url);
            stm.setString(1, RentalDate);
            stm.setString(2, RentalDate);
            stm.setString(3, ReturnDate);
            stm.setString(4, ReturnDate);
            stm.setString(5, RentalDate);
            stm.setString(6, RentalDate);
            stm.setString(7, ReturnDate);
            stm.setString(8, ReturnDate);
            stm.setString(9, "%" + name + "%");
            stm.setInt(10, offset);
            stm.setInt(11, next);

            rs = stm.executeQuery();
            while (rs.next()) {
                int resourceID = rs.getInt("resourceID");
                String resourceName = rs.getString("name");
                String color = rs.getString("color");;
                int quantity = rs.getInt("quantity");
                int category = rs.getInt("categoryID");
                int roleID = rs.getInt("roleID");
                TblResourceDTO dto = new TblResourceDTO(resourceID, resourceName, color, quantity, category, roleID);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
