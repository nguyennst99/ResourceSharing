/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblCategory;

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
public class TblCategoryDAO implements Serializable{
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
    
    public List<TblCategoryDTO> getAllCategory() throws SQLException, NamingException {
        List<TblCategoryDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            String url = "Select CategoryID, CategoryName From tbl_Category";
            stm = con.prepareStatement(url);
            rs = stm.executeQuery();

            while (rs.next()) {
                int categoryID = rs.getInt("CategoryID");
                String categoryName = rs.getString("CategoryName");

                list.add(new TblCategoryDTO(categoryID, categoryName));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public String findCategoryNameByID(int categoryID) throws SQLException, NamingException {
        String name = null;
        try {
            con = DBHelper.makeConnection();
            String url = "Select CategoryName From tbl_Category Where CategoryID = ?";
            stm = con.prepareStatement(url);
            stm.setInt(1, categoryID);
            rs = stm.executeQuery();

            if (rs.next()) {
                name = rs.getString("CategoryName");
            }
        } finally {
            closeConnection();
        }
        return name;
    }
}
