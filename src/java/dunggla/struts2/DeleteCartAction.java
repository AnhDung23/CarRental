/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cart.CartObj;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class DeleteCartAction {

    private int price;
    private final String SUCCESS = "success";

    public DeleteCartAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            CartObj cart = (CartObj) session.getAttribute("CART");
            if (cart != null) {
                cart.removeCarToCart(price);
                if (cart.getCars() == null) {
                    cart = null;
                }
                session.setAttribute("CART", cart);
            }
        }
        return url;
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

}
