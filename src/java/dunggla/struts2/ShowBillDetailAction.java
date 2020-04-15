/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggal.billdetails.BillDetailsDAO;
import dunggal.billdetails.BillDetailsDTO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class ShowBillDetailAction {
    private int idBill;
    private final String SUCCESS = "success";
    
    public ShowBillDetailAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute("ID_BILL", idBill);
            
            BillDetailsDAO dao = new BillDetailsDAO();
            dao.showBillDetailByID(idBill);
            List<BillDetailsDTO> list = dao.getList();
            request.setAttribute("LIST", list);
        }
        return "success";
    }

    /**
     * @return the idBill
     */
    public int getIdBill() {
        return idBill;
    }

    /**
     * @param idBill the idBill to set
     */
    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }
    
}
