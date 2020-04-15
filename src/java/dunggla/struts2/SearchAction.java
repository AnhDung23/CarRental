/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDAO;
import dunggla.cars.CarsDTO;
import dunggla.category.CategoryDAO;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class SearchAction {

    private String nameSearch;
    private String cateSearch;
    private String rentalDate;
    private String returnDate;
    private String amount;
    private Map<Integer, String> lisCategory;
    private final String SUCCESS = "success";

    public SearchAction() {
    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (getLisCategory() == null) {
            CategoryDAO cateDao = new CategoryDAO();
            cateDao.loadListCategory();
            setLisCategory(cateDao.getListCate());
        }
        boolean checkError = false;
        // Error date requied
        if (rentalDate.trim().equals("") || returnDate.trim().equals("")) {
            request.setAttribute("ERROR_DATE", "Rental date and return date are requied");
            checkError = true;
        }
        // Error date invalid format
        if (!CarsDTO.checkDateFormat(rentalDate) || !CarsDTO.checkDateFormat(returnDate)) {
            request.setAttribute("ERROR_DATE", "Rental date or return date is invalid");
            checkError = true;
        }
        // error amount format
        if (!amount.trim().matches("\\d{1,9}")) {
            request.setAttribute("ERROR_AMOUNT", "Amount is invalid");
            checkError = true;
        }
        // Requied search by name or category
        if (cateSearch.equals("-1") && nameSearch.equals("")) {
            request.setAttribute("ERROR_NAME_CATE", "Name or category is required");
            checkError = true;
        }

        if (checkError == false) {
            String status = "Active";
            // Change format of date from dd-MM-yyyy to yyyy-MM-dd
            Date rental = Date.valueOf(CarsDTO.changeDateFormat(rentalDate.trim()));
            Date returnD = Date.valueOf(CarsDTO.changeDateFormat(returnDate.trim()));

            // get current date
            long millis = System.currentTimeMillis();
            Timestamp currentDate = new Timestamp(millis);
            Date date = new Date(currentDate.getTime());

            // check date valid
            if (rental.toString().compareTo(date.toString()) < 0 || returnD.toString().compareTo(date.toString()) < 0) {
                    request.setAttribute("ERROR_DATE", "Rental and return date must be from current date");               
            } else {
                // rental earlier than return date
                if (rental.compareTo(returnD) <= 0) {
                    CarsDAO dao = new CarsDAO();
                    List<CarsDTO> list = null;
                    if (!nameSearch.trim().equals("") && cateSearch.equals("-1")) {
                        dao.searchByName(nameSearch.trim(), Integer.parseInt(amount.trim()), status);
                    } else if (nameSearch.trim().equals("") && !cateSearch.equals("-1")) {
                        dao.searchByCate(Integer.parseInt(cateSearch), Integer.parseInt(amount.trim()), status);
                    } else if (!nameSearch.trim().equals("") && !cateSearch.equals("-1")) {
                        dao.searchByNameAndCate(nameSearch, Integer.parseInt(cateSearch), Integer.parseInt(amount.trim()), status);
                    }
                    list = dao.getList();
                    request.setAttribute("LIST", list);
                } else {
                    request.setAttribute("ERROR_DATE", "Rental date is earlier return date");
                }
            }
        }

        return SUCCESS;
    }

    /**
     * @return the nameSearch
     */
    /**
     * @return the nameSearch
     */
    public String getNameSearch() {
        return nameSearch;
    }

    /**
     * @param nameSearch the nameSearch to set
     */
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    /**
     * @return the cateSearch
     */
    public String getCateSearch() {
        return cateSearch;
    }

    /**
     * @param cateSearch the cateSearch to set
     */
    public void setCateSearch(String cateSearch) {
        this.cateSearch = cateSearch;
    }

    /**
     * @return the rentalDate
     */
    public String getRentalDate() {
        return rentalDate;
    }

    /**
     * @param rentalDate the rentalDate to set
     */
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the lisCategory
     */
    public Map<Integer, String> getLisCategory() {
        return lisCategory;
    }

    /**
     * @param lisCategory the lisCategory to set
     */
    public void setLisCategory(Map<Integer, String> lisCategory) {
        this.lisCategory = lisCategory;
    }

    /**
     * @return the lisCategory
     */
    /**
     * @param lisCategory the lisCategory to set
     */
}
