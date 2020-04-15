/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDTO;
import dunggla.orderbills.OrderBillsDAO;
import dunggla.orderbills.OrderBillsDTO;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class SearchHistoryAction {

    private String nameSearch;
    private String orderFrom;
    private String orderTo;
    private final String SUCCESS = "success";

    public SearchHistoryAction() {
    }

    public String execute() throws Exception {
        String url = SUCCESS;
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        String status = "Active";
        OrderBillsDAO billDao = new OrderBillsDAO();
        List<OrderBillsDTO> list = null;

        // Error fiels are empty
        if (nameSearch.trim().equals("") && orderFrom.trim().equals("") && orderTo.trim().equals("")) {
            request.setAttribute("ERROR_SEARCH", "Name car or date order is required");

        } else if (!nameSearch.trim().equals("") && orderFrom.trim().equals("") && orderTo.trim().equals("")) {
            // Search by name
            billDao.searchNameHistoryBill(email, status, nameSearch.trim());
            list = billDao.getList();

        } else if (CarsDTO.checkDateFormat(orderFrom.trim()) && CarsDTO.checkDateFormat(orderTo.trim())) {
            // Search by date
            Timestamp dateFrom = Timestamp.valueOf(CarsDTO.changeDateFormat(orderFrom.trim()) + " 00:00:00.000");
            Timestamp dateTo = Timestamp.valueOf(CarsDTO.changeDateFormat(orderTo.trim()) + " 23:59:59.999");
            if (dateFrom.compareTo(dateTo) > 0) {
                request.setAttribute("ERROR_SEARCH", "The date from must be earlier than the date to");
            } else {
                if (nameSearch.trim().equals("")) {
                    billDao.searchByDateBill(email, status, dateFrom, dateTo);
                    list = billDao.getList();
                    
                } else {
                    billDao.searchByNameAndDateBill(email, status, dateFrom, dateTo, nameSearch.trim());
                    list = billDao.getList();
                    
                }
            }
            
        } else if (!CarsDTO.checkDateFormat(orderFrom.trim()) || !CarsDTO.checkDateFormat(orderTo.trim())) {
            request.setAttribute("ERROR_SEARCH", "Date format is invalid (dd-MM-yyyy)");
        }

        request.setAttribute("LIST", list);
        return "success";
    }

    /**
     * @return the nameSearch
     */
    public String getNameSearch() {
        return nameSearch;
    }

    /**
     * @param nameSearch the nameCar to set
     */
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    /**
     * @return the orderFrom
     */
    public String getOrderFrom() {
        return orderFrom;
    }

    /**
     * @param orderFrom the orderFrom to set
     */
    public void setOrderFrom(String orderFrom) {
        this.orderFrom = orderFrom;
    }

    /**
     * @return the orderTo
     */
    public String getOrderTo() {
        return orderTo;
    }

    /**
     * @param orderTo the orderTo to set
     */
    public void setOrderTo(String orderTo) {
        this.orderTo = orderTo;
    }

}
