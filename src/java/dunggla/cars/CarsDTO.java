/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.cars;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class CarsDTO implements Serializable{
    private String carName;
    private String color;
    private int price;
    private int quantity;
    private int  cateID;
    private String status;

    public CarsDTO() {
    }

    public CarsDTO(String carName, String color, int price, int quantity, int cateID, String status) {
        this.carName = carName;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.cateID = cateID;
        this.status = status;
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
     * @return the cateID
     */
    public int getCateID() {
        return cateID;
    }

    /**
     * @param cateID the cateID to set
     */
    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    // check date format dd-MM-yyyy
    public static boolean checkDateFormat(String date) throws ParseException{
        boolean check = false;
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);        
        try {
            df.parse(date);
            check = true;
        } catch (ParseException e) {
            check = false;
        }
        return check;
    }
    
    // Change date format to convert
    public static String changeDateFormat(String dmy){
        String[] ymd = dmy.split("-");
        return ymd[2] + "-" + ymd[1] + "-" + ymd[0];
    }
}
