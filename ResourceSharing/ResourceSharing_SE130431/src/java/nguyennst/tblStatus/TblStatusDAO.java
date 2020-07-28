/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nguyennst.tblStatus;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nguyennst.tblCategory.TblCategoryDTO;
import nguyennst.utils.DBHelper;

/**
 *
 * @author nguyennst
 */
public class TblStatusDAO implements Serializable{
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
    
    public List<TblStatusDTO> getAllStatus() throws SQLException, NamingException {
        List<TblStatusDTO> list = new ArrayList<>();
        try {
            con = DBHelper.makeConnection();
            String url = "Select statusID, statusName From tbl_Status";
            stm = con.prepareStatement(url);
            rs = stm.executeQuery();

            while (rs.next()) {
                int statusID = rs.getInt("statusID");
                String statusName = rs.getString("statusName");

                list.add(new TblStatusDTO(statusID, statusName));
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
