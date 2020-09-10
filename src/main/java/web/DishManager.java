package web;

import ejb.RequestBean;
import entity.Dish;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author zhao chenyang
 */
@Named
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class DishManager implements Serializable {
    @EJB
    private RequestBean requestBean;
    private String newDishId;
    private String newDishName;
    private String newDishPrice;
    private String newImageUrl;
    private String newType;

    public String getNewDishId() {
        return newDishId;
    }

    public void setNewDishId(String newDishId) {
        this.newDishId = newDishId;
    }

    public String getNewDishName() {
        return newDishName;
    }

    public void setNewDishName(String newDishName) {
        this.newDishName = newDishName;
    }

    public String getNewDishPrice() {
        return newDishPrice;
    }

    public void setNewDishPrice(String newDishPrice) {
        this.newDishPrice = newDishPrice;
    }

    public String getNewImageUrl() {
        return newImageUrl;
    }

    public void setNewImageUrl(String newImageUrl) {
        this.newImageUrl = newImageUrl;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public void createDish() {
        try {
            requestBean.createDish(newDishId, newDishName, newDishPrice, newImageUrl, newType);
        } catch (Exception e) {
            throw e;
        }
    }
}
