/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.feedbacks;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class FeedBackDTO implements Serializable{
    private int idFeedBack;
    private String email;
    private String carName;
    private String color;
    private int price;
    private String contentFB;
    private int rate;

    public FeedBackDTO() {
    }

    public FeedBackDTO(int idFeedBack, String email, String carName, String color, int price, String contentFB, int rate) {
        this.idFeedBack = idFeedBack;
        this.email = email;
        this.carName = carName;
        this.color = color;
        this.price = price;
        this.contentFB = contentFB;
        this.rate = rate;
    }

    /**
     * @return the idFeedBack
     */
    public int getIdFeedBack() {
        return idFeedBack;
    }

    /**
     * @param idFeedBack the idFeedBack to set
     */
    public void setIdFeedBack(int idFeedBack) {
        this.idFeedBack = idFeedBack;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the contentFB
     */
    public String getContentFB() {
        return contentFB;
    }

    /**
     * @param contentFB the contentFB to set
     */
    public void setContentFB(String contentFB) {
        this.contentFB = contentFB;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }
    
    
}
