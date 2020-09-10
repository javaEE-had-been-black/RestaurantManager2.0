package web;

import ejb.RequestBean;
import entity.Dish;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author zhao chenyang
 */
@Named
@SessionScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class DishManager implements Serializable {
    @EJB
    private RequestBean requestBean;
    private Dish dish;
    private String dishId;
    private String newDishId;
    private String newDishName;
    private String newDishPrice;
    private String newImageUrl;
    private String newType;
    private String dishName;
    private String dishType;
    private List<Dish> resultDish;

    public void createDish() {
        try {
            requestBean.createDish(newDishId, newDishName, newDishPrice, newImageUrl, newType);
        } catch (Exception e) {
            throw e;
        }
    }

    public void getDish() {
        try {
            dish = requestBean.getDishbyId(dishId);
        } catch (Exception e) {
            throw e;
        }
    }

    public void updateDish() {
        try {
            Dish dish = requestBean.getDishbyId(dishId);
            dish.setDishName(newDishName);
            dish.setDishPrice(newDishPrice);
            dish.setImageUrl(newImageUrl);
            dish.setType(newType);
        } catch (Exception e) {
            throw e;
        }
    }

    public void searchDish() {
        try {
            resultDish = requestBean.getDishesbyDishNameandType(dishName, dishType);
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeDish() {
        try {
            requestBean.removeDish(dishId);
        } catch (Exception e) {
            throw e;
        }
    }

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

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public RequestBean getRequestBean() {
        return requestBean;
    }

    public void setRequestBean(RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public List<Dish> getResultDish() {
        return resultDish;
    }

    public void setResultDish(List<Dish> resultDish) {
        this.resultDish = resultDish;
    }
}
