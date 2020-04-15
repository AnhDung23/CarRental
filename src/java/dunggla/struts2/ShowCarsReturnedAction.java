/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggal.billdetails.BillDetailsDAO;
import dunggal.billdetails.BillDetailsDTO;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class ShowCarsReturnedAction {

    private final String SUCCESS = "success";

    public ShowCarsReturnedAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            String email = (String) session.getAttribute("EMAIL");
            long millis = System.currentTimeMillis();
            Date currentDate = new Date(millis);
            System.out.println(currentDate);

            BillDetailsDAO dao = new BillDetailsDAO();
            dao.showCarReturned(email, currentDate);
            List<BillDetailsDTO> list = dao.getList();

            request.setAttribute("LIST", list);
        }
        return url;
    }

}
