/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.orderbills;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class OrderBillsDTO implements Serializable{
    private int idBill;
    private Timestamp orderDate;
    private int numOfCars;
    private float total;
    private String discountCode;

    public OrderBillsDTO() {
    }

    public OrderBillsDTO(int idBill, Timestamp orderDate, int numOfCars, float total, String discountCode) {
        this.idBill = idBill;
        this.orderDate = orderDate;
        this.numOfCars = numOfCars;
        this.total = total;
        this.discountCode = discountCode;
    }

    /**
     * @return the idBill
     */
    public int getIdBill() {
        return idBill;
    }

    /**
     * @param idBill the idBill to set
     */
    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    /**
     * @return the orderDate
     */
    public Timestamp getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the numOfCars
     */
    public int getNumOfCars() {
        return numOfCars;
    }

    /**
     * @param numOfCars the numOfCars to set
     */
    public void setNumOfCars(int numOfCars) {
        this.numOfCars = numOfCars;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(float total) {
        this.total = total;
    }

    /**
     * @return the discountCode
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * @param discountCode the discountCode to set
     */
    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
    
    
}
