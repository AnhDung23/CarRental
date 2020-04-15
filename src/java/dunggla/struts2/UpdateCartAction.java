/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDTO;
import dunggla.cart.CartObj;
import java.sql.Date;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class UpdateCartAction {

    private int price;
    private String amountAdd;
    private String rentalDate;
    private String returnDate;
    private final String SUCCESS = "success";

    public UpdateCartAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);

        if (CarsDTO.checkDateFormat(rentalDate.trim()) && CarsDTO.checkDateFormat(returnDate.trim())) {
            // check format dd-MM-yyyy
            if (!rentalDate.trim().matches("\\d{1,2}-\\d{1,2}-\\d{4}") || !returnDate.trim().matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
                request.setAttribute("ERR_DATE_UPDATE", "Date format is invalid (dd-MM-yyyy)");
            } else {
                // Case rental date later than return date
                if (rentalDate.trim().compareTo(returnDate.trim()) > 0) {
                    request.setAttribute("ERR_DATE_UPDATE", "The rental date must be earlier than the return date");
                } else {
                    // Case rental date earlier than return date
                    if (!amountAdd.trim().matches("\\d{1,9}") || Integer.parseInt(getAmountAdd().trim()) == 0) {
                        request.setAttribute("ERR_AMOUNT_UPDATE", "This must be from 1 to 9 numerals and greater then 0");;
                    } else {
                        // Change format of date from dd-MM-yyyy to yyyy-MM-dd
                        Date rental = Date.valueOf(CarsDTO.changeDateFormat(rentalDate.trim()));
                        Date returnD = Date.valueOf(CarsDTO.changeDateFormat(returnDate.trim()));

                        // get current date
                        long millis = System.currentTimeMillis();
                        Timestamp currentDate = new Timestamp(millis);
                        Date date = new Date(currentDate.getTime());
                        // check date valid
                        if (rental.toString().compareTo(date.toString()) < 0 || returnD.toString().compareTo(date.toString()) < 0) {
                            request.setAttribute("ERR_DATE_UPDATE", "Rental and return date must be from current date");
                        } else {
                            if (session != null) {
                                CartObj cart = (CartObj) session.getAttribute("CART");
                                if (cart != null) {
                                    cart.updateAmountCarInCart(price, Integer.parseInt(amountAdd.trim()), rentalDate.trim(), returnDate.trim());
                                    session.setAttribute("CART", cart);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            request.setAttribute("ERR_DATE_UPDATE", "Date format is invalid (dd-MM-yyyy)");
        }
        return url;
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
     * @return the amountAdd
     */
    public String getAmountAdd() {
        return amountAdd;
    }

    /**
     * @param amountAdd the amountAdd to set
     */
    public void setAmountAdd(String amountAdd) {
        this.amountAdd = amountAdd;
    }

}
