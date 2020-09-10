package web;

import ejb.RequestBean;
import entity.Dish;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
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
    private Integer dishId;
    private Integer newDishId;
    private String newDishName;
    private String newDishPrice;
    private String newImageUrl;
    private String newType;
    private String dishName;
    private String dishType;
    private String logInfo;
    private List<Dish> resultDish;
    //= requestBean.getAllDishes();

    public String createDish() {
        try {
            requestBean.createDish(newDishId, newDishName, newDishPrice, newImageUrl, newType);
            logInfo = "";
            return "success";
        } catch (Exception e) {
            logInfo = "创建菜品失败";
            return "fail";
        }
    }

    public String getDish() {
        try {
            dish = requestBean.getDishbyId(dishId);
            logInfo = "";
            return "success";
        } catch (Exception e) {
            logInfo = "获取菜品成功";
            return "fail";
        }
    }

    public String updateDish() {
        try {
            Dish dish = requestBean.getDishbyId(dishId);
            dish.setDishName(newDishName);
            dish.setDishPrice(newDishPrice);
            dish.setImageUrl(newImageUrl);
            dish.setType(newType);
            logInfo = "";
            return "success";
        } catch (Exception e) {
            logInfo = "更新菜品信息失败";
            return "fail";
        }
    }

    public String searchDish() {
        try {
            if ("全部".equals(dishType)) {
                Dish dish = requestBean.getDishbyName(dishName);
                if (resultDish == null) {
                    resultDish = new LinkedList<>();
                }
                resultDish.add(dish);
            } else {
                resultDish = requestBean.getDishesbyDishNameandType(dishName, dishType);
            }
            logInfo = "";
            return "success";
        } catch (Exception e) {
            logInfo = "搜索菜品信息失败";
            return "fail";
        }
    }

    public String removeDish() {
        try {
            requestBean.removeDish(dishId);
            logInfo = "";
            return "success";
        } catch (Exception e) {
            logInfo = "移除菜品信息失败";
            return "fail";
        }
    }

    public Integer getNewDishId() {
        return newDishId;
    }

    public void setNewDishId(Integer newDishId) {
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
        return dishId + "";
    }

    public void setDishId(String dishId) {
        this.dishId = Integer.parseInt(dishId);
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
