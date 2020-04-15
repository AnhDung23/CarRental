/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggal.billdetails;

import dunggla.utils.DBUntilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class BillDetailsDAO implements Serializable {

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

    // Check out Bill detail
    public boolean checkOutDetail(String email, int price, String carName, String color, int quantity, Date rentalDate, Date returnDate, int total, int idBill)
            throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Insert BillDetails(Email,Price,carName,color,quantity,rentalDate,returnDate,total,idBill) "
                        + "Values(?,?,?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setInt(2, price);
                stmt.setString(3, carName);
                stmt.setString(4, color);
                stmt.setInt(5, quantity);
                stmt.setDate(6, rentalDate);
                stmt.setDate(7, returnDate);
                stmt.setInt(8, total);
                stmt.setInt(9, idBill);
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    // Get amount car by date
    public int getAmountByDate(int price, Date rentalD, Date returnD) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        int amount = 0;
        try {
            if (con != null) {
                String sql = "Select SUM(quantity) as amount "
                        + "From BillDetails "
                        + "Where Price = ? and rentalDate <= ? and returnDate >= ? ";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, price);
                stmt.setDate(2, returnD);
                stmt.setDate(3, rentalD);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    amount = rs.getInt("amount");
                }
            }
        } finally {
            closeConnection();
        }
        return amount;
    }
    
    private List<BillDetailsDTO> list;

    public List<BillDetailsDTO> getList() {
        return list;
    }
    
    // get list detail of bill by id
    public void showBillDetailByID(int idBill) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select carName, color, Price, quantity, rentalDate, returnDate, total "
                        + "From BillDetails "
                        + "Where idBill = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, idBill);
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("quantity");
                    Date rentalDate = rs.getDate("rentalDate");
                    Date returnDate = rs.getDate("returnDate");
                    int total = rs.getInt("total");
                    BillDetailsDTO dto = new BillDetailsDTO(carName, color, price, quantity, rentalDate, returnDate, total);
                    
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
    
    // Get list car returned
    public void showCarReturned(String email, Date currentDate) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select carName, color, Price, quantity, rentalDate, returnDate, total "
                        + "From BillDetails "
                        + "Where Email = ? and returnDate <= ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setDate(2, currentDate);
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("Price");
                    int quantity = rs.getInt("quantity");
                    Date rentalDate = rs.getDate("rentalDate");
                    Date returnDate = rs.getDate("returnDate");
                    int total = rs.getInt("total");
                    BillDetailsDTO dto = new BillDetailsDTO(carName, color, price, quantity, rentalDate, returnDate, total);
                    
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
