/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartObj implements Serializable{
    private String username;
    private Map<Integer, CarDetailInCartDTO> cars;

    public CartObj() {
    }

    public CartObj(String username, Map<Integer, CarDetailInCartDTO> cars) {
        this.username = username;
        this.cars = cars;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the cars
     */
    public Map<Integer, CarDetailInCartDTO> getCars() {
        return cars;
    }
    
    // Add car to cart
    public void addCarToCart(CarDetailInCartDTO dto){
        if (this.cars == null) {
            this.cars = new HashMap<>();
        }
        
        int key = dto.getPrice();
        if (this.cars.containsKey(key)) {
            int oldQuantity = this.cars.get(key).getAmountAdd();
            int quantityAdd = dto.getAmountAdd();
            this.cars.get(key).setAmountAdd(oldQuantity + quantityAdd);
            this.cars.get(key).setRentalDate(dto.getRentalDate());
            this.cars.get(key).setReturnDate(dto.getReturnDate());
        } else {
            this.cars.put(key, dto);
        }
    }
    
    // Remove car to cart
    public void removeCarToCart(int priceKey){
        if (this.cars == null) {
            return;
        }
        if (this.cars.containsKey(priceKey)) {
            this.cars.remove(priceKey);
            if (this.cars.isEmpty()) {
                this.cars = null;
            }
        }
    }
    
    // Update amount car in cart
    public void updateAmountCarInCart(int priceKey, int amountUpdate, String rentalDate, String returnDate){
        if (this.cars == null) {
            return;
        }
        if (this.cars.containsKey(priceKey)) {
            this.cars.get(priceKey).setAmountAdd(amountUpdate);
            this.cars.get(priceKey).setRentalDate(rentalDate);
            this.cars.get(priceKey).setReturnDate(returnDate);
        }
    }
    
}
