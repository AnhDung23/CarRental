/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDAO;
import dunggla.cars.CarsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class ShowFeedBackPageAction {
    private int price;
    private final String SUCCESS = "success";
    
    public ShowFeedBackPageAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        
        CarsDAO dao = new CarsDAO();
        CarsDTO dto = dao.getCarDetail(price);
        session.setAttribute("DTO", dto);
        
        List<Integer> listRate = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            listRate.add(i);
        }
        session.setAttribute("LIST_RATE", listRate);
        
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
