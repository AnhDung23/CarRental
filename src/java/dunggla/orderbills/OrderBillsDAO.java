/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.orderbills;

import dunggla.utils.DBUntilies;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderBillsDAO implements Serializable{
    
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
    
    // Get max id of bill to add new bill (id new bill = max + 1)
    public int getMaxIDBill() throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        int max = 0;
        try {
            if (con != null) {
                String sql = "Select MAX(idBill) as maxID "
                        + "From OrderBills";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    max = rs.getInt("maxID");
                }
            }
        } finally {
            closeConnection();
        }
        return max;
    }
    
    // Check out bill to database
    public boolean checkOutBill(int id, String email, Timestamp orderDate, int numOfCars, float total, String discountCode, String status) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        boolean check = false;
        try {
            if (con != null) {
                String sql = "Insert OrderBills(idBill,Email,orderDate,numOfCars,total,DiscountCode,Status) "
                        + "Values(?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.setString(2, email);
                stmt.setTimestamp(3, orderDate);
                stmt.setInt(4, numOfCars);
                stmt.setFloat(5, total);
                stmt.setString(6, discountCode);
                stmt.setString(7, status);
                check = stmt.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
    
    private List<OrderBillsDTO> list;

    public List<OrderBillsDTO> getList() {
        return list;
    }
    
    // Show history shopping of user
    public void showAllHistoryBill(String email, String status) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select idBill, orderDate, numOfCars, total, DiscountCode "
                        + "From OrderBills "
                        + "Where Email = ? and Status = ? "
                        + "Order by orderDate DESC";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, status);
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    int idBill = rs.getInt("idBill");
                    Timestamp orderDate = rs.getTimestamp("orderDate");
                    int numOfCars = rs.getInt("numOfCars");
                    float total = rs.getFloat("total");
                    String discountCode = rs.getString("DiscountCode");
                    OrderBillsDTO dto = new OrderBillsDTO(idBill, orderDate, numOfCars, total, discountCode);
                    
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
    
    // Delete a bill
    public boolean deleteOrderBill(int idBill, String status) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        boolean check = false;
        try {
            String sql = "Update OrderBills "
                    + "Set Status = ? "
                    + "Where idBill = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, idBill);
            check = stmt.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
    
    // Get list bill by name user
    public void searchNameHistoryBill(String email, String status, String nameSearch) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select idBill, orderDate, numOfCars, total, DiscountCode "
                        + "From OrderBills "
                        + "Where Email = ? and Status = ? and idBill in "
                        + "(Select idBill "
                        + "From BillDetails "
                        + "Where carName like ?) "
                        + "Order by orderDate DESC";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, status);
                stmt.setString(3, "%" + nameSearch + "%");
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    int idBill = rs.getInt("idBill");
                    Timestamp orderDate = rs.getTimestamp("orderDate");
                    int numOfCars = rs.getInt("numOfCars");
                    float total = rs.getFloat("total");
                    String discountCode = rs.getString("DiscountCode");
                    OrderBillsDTO dto = new OrderBillsDTO(idBill, orderDate, numOfCars, total, discountCode);
                    
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
    
    // Get list bill search by date
    public void searchByDateBill(String email, String status, Timestamp dateFrom, Timestamp dateTo) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select idBill, orderDate, numOfCars, total, DiscountCode "
                        + "From OrderBills "
                        + "Where Email = ? and Status = ? and orderDate >= ? and orderDate <= ? "
                        + "Order by orderDate DESC";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, status);
                stmt.setTimestamp(3, dateFrom);
                stmt.setTimestamp(4, dateTo);
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    int idBill = rs.getInt("idBill");
                    Timestamp orderDate = rs.getTimestamp("orderDate");
                    int numOfCars = rs.getInt("numOfCars");
                    float total = rs.getFloat("total");
                    String discountCode = rs.getString("DiscountCode");
                    OrderBillsDTO dto = new OrderBillsDTO(idBill, orderDate, numOfCars, total, discountCode);
                    
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
    
    // Get list bill by name user and date
    public void searchByNameAndDateBill(String email, String status, Timestamp dateFrom, Timestamp dateTo, String nameSearch) throws NamingException, SQLException{
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select idBill, orderDate, numOfCars, total, DiscountCode "
                        + "From OrderBills "
                        + "Where Email = ? and Status = ? and orderDate >= ? and orderDate <= ? and idBill in "
                        + "(Select idBill "
                        + "From BillDetails "
                        + "Where carName like ?) "
                        + "Order by orderDate DESC";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, email);
                stmt.setString(2, status);
                stmt.setTimestamp(3, dateFrom);
                stmt.setTimestamp(4, dateTo);
                stmt.setString(5, "%" + nameSearch + "%");
                rs = stmt.executeQuery();
                while (rs.next()) {                    
                    int idBill = rs.getInt("idBill");
                    Timestamp orderDate = rs.getTimestamp("orderDate");
                    int numOfCars = rs.getInt("numOfCars");
                    float total = rs.getFloat("total");
                    String discountCode = rs.getString("DiscountCode");
                    OrderBillsDTO dto = new OrderBillsDTO(idBill, orderDate, numOfCars, total, discountCode);
                    
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
