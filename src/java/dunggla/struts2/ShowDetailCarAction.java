/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDAO;
import dunggla.cars.CarsDTO;
import dunggla.feedbacks.FeedBackDTO;
import dunggla.feedbacks.FeedBacksDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class ShowDetailCarAction {
    private int price;
    private final String SUCCESS = "success";
    public ShowDetailCarAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        
        CarsDAO dao = new CarsDAO();
        CarsDTO dto = dao.getCarDetail(price);
        request.setAttribute("DTO", dto);
        
        FeedBacksDAO fbDao = new FeedBacksDAO();
        fbDao.getFeedBack(price);
        List<FeedBackDTO> list = fbDao.getList();

        request.setAttribute("LIST_FB", list);
        
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
