/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.orderbills.OrderBillsDAO;
import dunggla.orderbills.OrderBillsDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class ShowOrderHistoryAction {
    private final String SUCCESS = "success";
    
    public ShowOrderHistoryAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            String email = (String) session.getAttribute("EMAIL");
            if (email != null) {
                String status = "Active";
                OrderBillsDAO dao = new OrderBillsDAO();
                dao.showAllHistoryBill(email, status);
                List<OrderBillsDTO> list = dao.getList();
                request.setAttribute("LIST", list);
            }
        }
        
        return url;
    }
    
}
