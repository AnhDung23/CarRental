/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dunggla.category;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CategoryDTO implements Serializable{
    private int categoryID;
    private String nameCategory;

    public CategoryDTO() {
    }

    public CategoryDTO(int categoryID, String nameCategory) {
        this.categoryID = categoryID;
        this.nameCategory = nameCategory;
    }

    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the nameCategory
     */
    public String getNameCategory() {
        return nameCategory;
    }

    /**
     * @param nameCategory the nameCategory to set
     */
    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
    
    
}
