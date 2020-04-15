/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.registrations;

import dunggla.emailservice.EmailService;
import dunggla.utils.DBUntilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

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
    
    // Create new account
    public boolean createAccount(String email, String password, String fullname, String phone, String address, Timestamp createDate, String role, String status, String code)
        throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Insert Registrations(Email,Password,Fullname,Phone,Address,createDate,Role,Status) "
                        + "Values (?,?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.setString(3, fullname);
                stmt.setString(4, phone);
                stmt.setString(5, address);
                stmt.setTimestamp(6, createDate);
                stmt.setString(7, role);
                stmt.setString(8, status);
                
                check = stmt.executeUpdate() > 0;
                if (check) {
                    EmailService es = new EmailService(email, code);
                    es.verifyEmail();
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    // Verify account success
    public boolean activeAccount(String email, String status) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Update Registrations "
                        + "Set Status = ? "
                        + "Where Email = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, status);
                stmt.setString(2, email);
                
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    // Check login user
    public String checkLogin(String email, String password, String status) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        String role = "fail";
        try {
            if (con != null) {
                String sql = "Select Role "
                        + "From Registrations "
                        + "Where Email = ? and Password = ? and Status = ? ";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, password);
                stmt.setString(3, status);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    role = rs.getString("Role");
                }
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    // Get name user
    public String getNameUser(String email) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        String name = "";
        try {
            if (con != null) {
                String sql = "Select Fullname "
                        + "From Registrations "
                        + "Where Email = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    name = rs.getString("Fullname");
                }
            }
        } finally {
            closeConnection();
        }
        return name;
    }
}
