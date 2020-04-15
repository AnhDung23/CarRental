/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDTO;
import dunggla.feedbacks.FeedBacksDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class FeedBackAction {

    private String contentFB;
    private int rating;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public FeedBackAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);

        // if feedback file is empty or don't choose rating
        if (contentFB.trim().equals("") || rating == -1) {
            request.setAttribute("ERR_FB", "All fiels are required");
        } else {
            if (session != null) {
                String email = (String) session.getAttribute("EMAIL");
                FeedBacksDAO dao = new FeedBacksDAO();
                int idFB = dao.getMaxIdFB() + 1;
                CarsDTO dto = (CarsDTO) session.getAttribute("DTO");
                if (dto != null) {
                    String carName = dto.getCarName();
                    String color = dto.getColor();
                    int price = dto.getPrice();
                    boolean check = dao.addFeedBack(idFB, email, carName, color, price, contentFB, rating);
                    if (check) {
                        request.setAttribute("FB", "Feed back successful");
                    } else {
                        url = FAIL;
                    }
                }
            }

        }
        return url;
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
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

}
