/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggal.billdetails.BillDetailsDAO;
import dunggal.billdetails.BillDetailsDTO;
import dunggal.discountcode.DiscountCodeDAO;
import dunggla.cars.CarsDAO;
import dunggla.cart.CarDetailInCartDTO;
import dunggla.cart.CartObj;
import dunggla.orderbills.OrderBillsDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class CheckoutCartAction {

    private int total;
    private String discountCode;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public CheckoutCartAction() {
    }

    public String execute() throws Exception {
        String url = FAIL;
        boolean checkConfirm = false;
        boolean checkValid = true;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        BillDetailsDTO detailDto = new BillDetailsDTO();

        System.out.println(discountCode);
        if (session != null) {
            CartObj cart = (CartObj) session.getAttribute("CART");

            if (cart != null) {
                // get user rental
                String email = (String) session.getAttribute("EMAIL");
                cart.setUsername(email);
                Map<Integer, CarDetailInCartDTO> cars = cart.getCars();

                if (cars != null) {
                    CarsDAO carDao = new CarsDAO();
                    BillDetailsDAO detailDao = new BillDetailsDAO();
                    for (Integer price : cars.keySet()) {
                        int amountInStock = carDao.getAmountInStock(price);
                        String carName = cars.get(price).getCarName();
                        int quantity = cars.get(price).getAmountAdd();
                        Date rentalDate = Date.valueOf(detailDto.changeDateFormat(cars.get(price).getRentalDate()));
                        Date returnDate = Date.valueOf(detailDto.changeDateFormat(cars.get(price).getReturnDate()));
                        int amountBought = detailDao.getAmountByDate(price, rentalDate, returnDate);

                        // amount out of stock
                        if (amountInStock - amountBought < quantity) {
                            request.setAttribute("ERROR_CONFIRM", "Quantity of " + carName + "(" + price + ") is out of stock: " + (amountInStock - amountBought));
                            checkValid = false;
                            break;
                        }
                    }
                    // Amount valid
                    if (checkValid) {
                        OrderBillsDAO billDao = new OrderBillsDAO();
                        long millis = System.currentTimeMillis();
                        Timestamp orderDate = new Timestamp(millis);
                        int idBill = billDao.getMaxIDBill() + 1;

                        int value = 0;
                        float totalBill = total;
                        // User has discount code
                        if (discountCode != null) {
                            DiscountCodeDAO codeDao = new DiscountCodeDAO();
                            Date date = new Date(orderDate.getTime());
                            value = codeDao.getValueOfCode(discountCode.trim(), date);
                        }
                        
                        // User has discount code but this code is invalid
                        if (!discountCode.equals("") && value == 0) {
                            request.setAttribute("INVALID_CODE", "Your discount code is invalid");
                        }
                        // Discount code is valid
                        if (value != 0) {
                            totalBill = total - total * value / 100;
                        } else {
                            discountCode = null;
                        }
                        String status = "Active";
                        checkConfirm = billDao.checkOutBill(idBill, email, orderDate, cars.size(), totalBill, discountCode, status);

                        if (checkConfirm) {
                            for (Integer price : cars.keySet()) {
                                String carName = cars.get(price).getCarName();
                                String color = cars.get(price).getColor();
                                int quantity = cars.get(price).getAmountAdd();
                                Date rentalDate = Date.valueOf(detailDto.changeDateFormat(cars.get(price).getRentalDate()));
                                Date returnDate = Date.valueOf(detailDto.changeDateFormat(cars.get(price).getReturnDate()));
                                int subTotal = price * quantity;

                                checkConfirm = detailDao.checkOutDetail(email, price, carName, color, quantity, rentalDate, returnDate, subTotal, idBill);
                            }
                            if (checkConfirm) {
                                url = SUCCESS;
                                cart = null;
                                session.setAttribute("CART", cart);
                                request.setAttribute("CHECKOUT_MSG", "Check out successful!!");

                            }
                        }
                    }
                }
            }
        }
        return SUCCESS;
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
