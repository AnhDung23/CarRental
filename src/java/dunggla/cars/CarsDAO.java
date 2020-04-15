/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.cars;

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
public class CarsDAO implements Serializable {

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

    private List<CarsDTO> list;

    public List<CarsDTO> getList() {
        return list;
    }

    // Get list all car
    public void showAllbyMember() throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select carName, color, price, quantity, categoryID, status "
                        + "From Cars";
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("categoryID");
                    String status = rs.getString("status");

                    CarsDTO dto = new CarsDTO(carName, color, price, quantity, categoryID, status);
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

    // Get amount in stock
    public int getAmountInStock(int price) throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        int amount = 0;
        try {
            if (con != null) {
                String sql = "Select quantity "
                        + "From Cars "
                        + "Where price = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, price);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    amount = rs.getInt("quantity");
                }
            }
        } finally {
            closeConnection();
        }
        return amount;
    }

    // Get detail of a car
    public CarsDTO getCarDetail(int priceFind) throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        CarsDTO dto = null;
        try {
            if (con != null) {
                String sql = "Select carName, color, price, quantity, categoryID, status "
                        + "From Cars "
                        + "Where price = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, priceFind);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("categoryID");
                    String status = rs.getString("status");

                    dto = new CarsDTO(carName, color, priceFind, quantity, categoryID, status);
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    // Get list car search all information
    public void searchByName(String nameSearch, int amount, String stt) throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select carName, color, price, quantity, categoryID, status "
                        + "From Cars "
                        + "Where carName like ? and quantity >= ? and status = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, "%" + nameSearch + "%");
                stmt.setInt(2, amount);
                stmt.setString(3, stt);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("categoryID");
                    String status = rs.getString("status");
                    
                    CarsDTO dto = new CarsDTO(carName, color, price, quantity, categoryID, status);
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
    
    // Get list car search by category
    public void searchByCate(int cateID, int amount, String stt) throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select carName, color, price, quantity, categoryID, status "
                        + "From Cars "
                        + "Where quantity >= ? and categoryID = ? and status = ?";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, amount);
                stmt.setInt(2, cateID);
                stmt.setString(3, stt);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("categoryID");
                    String status = rs.getString("status");
                    
                    CarsDTO dto = new CarsDTO(carName, color, price, quantity, categoryID, status);
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
    
    // Get list car search by category and name
    public void searchByNameAndCate(String nameSearch, int cateID, int amount, String stt) throws NamingException, SQLException {
        con = DBUntilies.makeConnection();
        try {
            if (con != null) {
                String sql = "Select carName, color, price, quantity, categoryID, status "
                        + "From Cars "
                        + "Where quantity >= ? and categoryID = ? and status = ? and carName like ? ";
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, amount);
                stmt.setInt(2, cateID);
                stmt.setString(3, stt);
                stmt.setString(4, "%" + nameSearch + "%");
                rs = stmt.executeQuery();
                while (rs.next()) {
                    String carName = rs.getString("carName");
                    String color = rs.getString("color");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    int categoryID = rs.getInt("categoryID");
                    String status = rs.getString("status");
                    
                    CarsDTO dto = new CarsDTO(carName, color, price, quantity, categoryID, status);
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
