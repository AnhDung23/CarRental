/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.feedbacks;

import dunggla.utils.DBUntilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class FeedBacksDAO implements Serializable{
    
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;

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
    
    // Get max ID to insert new fb ( id new fb = max + 1)
    public int getMaxIdFB() throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        int max = 0;
        try {
            if (con != null) {
                String sql = "Select MAX(idFeedBack) as max "
                        + "From FeedBacks";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    max = rs.getInt("max");
                }
            }
        } finally{
            closeConnection();
        }
        return max;
    }
    
    // Insert feedback
    public boolean addFeedBack(int id, String email, String nameCar, String color, int price, String contentFB, int rate) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Insert FeedBacks(idFeedBack,email,carName,color,price,contentFB,rate) "
                        + "Values(?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, email);
                stmt.setString(3, nameCar);
                stmt.setString(4, color);
                stmt.setInt(5, price);
                stmt.setString(6, contentFB);
                stmt.setInt(7, rate);
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    private List<FeedBackDTO> list;

    public List<FeedBackDTO> getList() {
        return list;
    }
    
    // Get fb of a car
    public void getFeedBack(int priceFind) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select idFeedBack, email, carName, color, price, contentFB, rate "
                        + "From FeedBacks "
                        + "Where price = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, priceFind);
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    int idFeedBack = rs.getInt("idFeedBack");
                    String email = rs.getString("email");
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("price");
                    String contentFB = rs.getString("contentFB");
                    int rate = rs.getInt("rate");
                    FeedBackDTO dto = new FeedBackDTO(idFeedBack, email, carName, color, price, contentFB, rate);
                    if (this.list == null) {
                        this.list = new ArrayList<>();
                    }
                    this.list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
    }
}
