/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cart.CarDetailInCartDTO;
import dunggla.cart.CartObj;
import dunggla.category.CategoryDAO;
import java.sql.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class AddToCartAction {

    private String nameSearch;
    private String cateSearch;
    private String amount;
    
    private String carName;
    private String color;
    private int price;
    private int cateID;
    private String amountAdd;
    private String rentalDate;
    private String returnDate;
    private final String SUCCESS = "success";
    private final String NULLSEARCH = "nullsearch";

    public AddToCartAction() {
    }

    public String execute() throws Exception {
        String url = NULLSEARCH;
        HttpServletRequest request = ServletActionContext.getRequest();
        System.out.println("Name " + carName);
        System.out.println("color " + color);
        System.out.println("price " + price);
        System.out.println("id " + cateID);
        System.out.println("amountAdd " + amountAdd);
        System.out.println("search "+nameSearch);
        System.out.println("search cate "+cateSearch);
        System.out.println("search amount "+amount);
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart == null) {
                cart = new CartObj();
            }

            // Case don't search
            if (rentalDate.equals("") && returnDate.equals("")) {             
                // Get current time
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                // date : dd-MM-yyyy
                String[] dmy = date.toString().split("-");
                // change date to: yyyy-MM-dd
                String dmyFormat = dmy[2] + "-" + dmy[1] + "-" + dmy[0];
                
                int quantity = Integer.parseInt(amountAdd);
                CategoryDAO cateDao = new CategoryDAO();
                String cate = cateDao.getCateByID(cateID);
                CarDetailInCartDTO dto = new CarDetailInCartDTO(carName, color, price, cate, quantity, dmyFormat, dmyFormat);
                cart.addCarToCart(dto);
                Map<Integer,CarDetailInCartDTO> map = cart.getCars();
                
                session.setAttribute("CART", cart);
            } else {
                // Case search
                int quantity = Integer.parseInt(amount.trim());
                CategoryDAO cateDao = new CategoryDAO();
                String cate = cateDao.getCateByID(cateID);
                CarDetailInCartDTO dto = new CarDetailInCartDTO(carName, color, price, cate, quantity, rentalDate, returnDate);
                cart.addCarToCart(dto);
                Map<Integer,CarDetailInCartDTO> map = cart.getCars();
                
                session.setAttribute("CART", cart);
                url = SUCCESS;
            }
        }
        return url;
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

    /**
     * @return the nameSearch
     */
    public String getNameSearch() {
        return nameSearch;
    }

    /**
     * @param nameSearch the nameSearch to set
     */
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    /**
     * @return the cateSearch
     */
    public String getCateSearch() {
        return cateSearch;
    }

    /**
     * @param cateSearch the cateSearch to set
     */
    public void setCateSearch(String cateSearch) {
        this.cateSearch = cateSearch;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
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
