/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.category;

import dunggla.utils.DBUntilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CategoryDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

    // Close connection with SQL Server
    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    private Map<Integer,String> listCate;

    public Map<Integer,String> getListCate() {
        return listCate;
    }
    
    // Get list all category
    public void loadListCategory() throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select categoryID, nameCategory "
                        + "From Category";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {     
                    int cateID = rs.getInt("categoryID");
                    String name = rs.getString("nameCategory");
                    CategoryDTO dto = new CategoryDTO(cateID, name);
                    
                    if (this.listCate == null) {
                        this.listCate = new HashMap<>();
                    }
                    
                    this.listCate.put(cateID, name);
                }
            }
        } finally {
            closeConnection();
        }
    }
    
    // Get category by ID
    public String getCateByID(int id) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        String cate = "";
        try {
            if (con != null) {
                String sql = "Select nameCategory "
                        + "From Category "
                        + "Where categoryID = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    cate = rs.getString("nameCategory");
                }
            }
        } finally {
            closeConnection();
        }
        return cate;
    }
}
