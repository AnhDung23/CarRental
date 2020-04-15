/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.cart;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CarDetailInCartDTO implements Serializable{
    private String carName;
    private String color;
    private int price;
    private String cate;
    private int amountAdd;
    private String rentalDate;
    private String returnDate;

    public CarDetailInCartDTO() {
    }

    public CarDetailInCartDTO(String carName, String color, int price, String cate, int amountAdd, String rentalDate, String returnDate) {
        this.carName = carName;
        this.color = color;
        this.price = price;
        this.cate = cate;
        this.amountAdd = amountAdd;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
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
     * @return the cateID
     */
    public String getCate() {
        return cate;
    }

    /**
     * @param cate the cateID to set
     */
    public void setCateID(String cate) {
        this.cate = cate;
    }

    /**
     * @return the amountAdd
     */
    public int getAmountAdd() {
        return amountAdd;
    }

    /**
     * @param amountAdd the amountAdd to set
     */
    public void setAmountAdd(int amountAdd) {
        this.amountAdd = amountAdd;
    }

    /**
     * @return the rentalDate
     */
    public String getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

}
