/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggal.discountcode;

import dunggla.utils.DBUntilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class DiscountCodeDAO implements Serializable{
    
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
    
    // Get value of dicount code
    public int getValueOfCode(String code, Date date) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        int value = 0;
        try {
            if (con != null) {
                String sql = "Select value "
                        + "From DiscountCodes "
                        + "Where dateFrom <= ? and dateTo >= ? and DiscountCode = ?";
                stmt = con.prepareStatement(sql);
                stmt.setDate(1, date);
                stmt.setDate(2, date);
                stmt.setString(3, code);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    value = rs.getInt("value");
                }
            }
        } finally {
            closeConnection();
        }
        return value;
    }
}
