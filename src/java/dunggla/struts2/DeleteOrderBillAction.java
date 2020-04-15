/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.orderbills.OrderBillsDAO;

/**
 *
 * @author Admin
 */
public class DeleteOrderBillAction {
    private int idBill;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    
    public DeleteOrderBillAction() {
    }
    
    public String execute() throws Exception {
        String url = FAIL;
        String status = "Inactive";
        OrderBillsDAO dao = new OrderBillsDAO();
        boolean check = dao.deleteOrderBill(idBill, status);
        if (check) {
            url = SUCCESS;
        }      
        return url;
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
