/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggal.billdetails;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class BillDetailsDTO implements Serializable{
    private String carName;
    private String color;
    private int price;
    private int quantity;
    private Date rentalDate;
    private Date returnDate;
    private int total;

    public BillDetailsDTO() {
    }

    public BillDetailsDTO(String carName, String color, int price, int quantity, Date rentalDate, Date returnDate, int total) {
        this.carName = carName;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.total = total;
    }
    
    // change format to convert
    public String changeDateFormat(String dmy){
        String[] ymd = dmy.split("-");
        return ymd[2] + "-" + ymd[1] + "-" + ymd[0];
    }

    /**
     * @return the carName
     */
    public String getCarName() {
        return carName;
    }

    /**
     * @param carName the carName to set
     */
    public void setCarName(String carName) {
        this.carName = carName;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the rentalDate
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the returnDate
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }
    
}
