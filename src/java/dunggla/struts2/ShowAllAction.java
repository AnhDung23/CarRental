/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.struts2;

import dunggla.cars.CarsDAO;
import dunggla.cars.CarsDTO;
import dunggla.category.CategoryDAO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Admin
 */
public class ShowAllAction {
    private final String SUCCESS = "success";
    private Map<Integer,String> lisCategory;
    
    public ShowAllAction() {
    }
    
    public String execute() throws Exception {
        String url = SUCCESS;
        
        HttpServletRequest request = ServletActionContext.getRequest();

        if (lisCategory == null) {
            CategoryDAO cateDao = new CategoryDAO();
            cateDao.loadListCategory();
            lisCategory = cateDao.getListCate();
        }
        CarsDAO dao = new CarsDAO();
        dao.showAllbyMember();
        List<CarsDTO> list = dao.getList();
        request.setAttribute("LIST", list);
        return url;
    }

    /**
     * @return the lisCategory
     */
    public Map<Integer,String> getLisCategory() {
        return lisCategory;
    }

    /**
     * @param lisCategory the lisCategory to set
     */
    public void setLisCategory(Map<Integer,String> lisCategory) {
        this.lisCategory = lisCategory;
    }
    
    
}
