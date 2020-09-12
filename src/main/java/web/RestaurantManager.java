package web;

import ejb.RequestBean;
import entity.*;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author zhang wen tao
 */

@Named
@SessionScoped
@SuppressWarnings("unused")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class RestaurantManager implements Serializable {
    @EJB
    private RequestBean request;
    private String userId;
    private String password;
    private String position;
    private String userName;
    private String salary;
    private List<Dish> dishes;
    private String logInfo;
    private String discount;
    private String newUserId;
    private String newUserName;
    private String newPassword;
    private String newPosition;
    private String newTelNumber;
    private String newSalary;
    private String customerTelNumber;
    private User currentUser;
    private String comment;
    private String dishInfo;

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String currentUserId;
    private List<Dish> allDishes;

    public String getCurrentUserId() {

        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {

        currentUser=request.getUserbyUserId(currentUserId);
        this.currentUserId = currentUserId;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    //獲取指定用戶的用戶信息
    public void setCurrentUser(String userId) {
        currentUser = request.getUserbyUserId(userId);
    }

    public RestaurantManager() {
    }


    public void setCustomerTelNumber(String customerTelNumber) {
        this.customerTelNumber = customerTelNumber;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNewUserId() {
        return newUserId;
    }

    public void setNewUserId(String newUserId) {
        this.newUserId = newUserId;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(String newPosition) {
        this.newPosition = newPosition;
    }

    public String getNewTelNumber() {
        return newTelNumber;
    }

    public void setNewTelNumber(String newTelNumber) {
        this.newTelNumber = newTelNumber;
    }

    public String getNewSalary() {
        return newSalary;
    }

    public void setNewSalary(String newSalary) {
        this.newSalary = newSalary;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public String getUserName() {
        return userName;
    }

    public String getSalary() {
        return salary;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    private static final Logger logger = Logger.getLogger("web.RestaurantManager");

    private Integer currentOrder;
    private String currentDish;
    // 添加座位
    // 删除座位
    // 修改状态
    /*
     * Seat
     */


    /*
      User
     */

    /**
     * 判断User是否存在
     *
     * @param userId userid
     * @return 返回bool值表示user是否存在
     */
    public boolean hasUser(String userId) {
        return request.getUserbyUserId(userId) != null;
    }

    public User getUser(String userId) {
        try {
            return request.getUserbyUserId(userId);
        } catch (EJBException e) {
            throw e;
        }
    }

    public String getCreateUserInfo() {
        return createUserInfo;
    }

    public void setCreateUserInfo(String createUserInfo) {
        this.createUserInfo = createUserInfo;
    }

    /**
     * 添加user
     */

    private String createUserInfo;

    public void createUser() {
        try {
            request.createUser(newTelNumber, newUserName, newPassword, newPosition, newTelNumber, newSalary);

            userResult = getAllUsers();
            createUserInfo = "创建成功";
        } catch (Exception e) {
            logger.warning("Create User Failed,the reason is" + e.getMessage());
            createUserInfo = "创建失败！";
            throw e;
        }
    }

    /**
     * 删除user
     */
    private String removeUserInfo;

    public String getRemoveUserInfo() {
        return removeUserInfo;
    }

    public void setRemoveUserInfo(String removeUserInfo) {
        this.removeUserInfo = removeUserInfo;
    }

    public void setCurrentUserId(ActionEvent event) {
        UIParameter param = null;
        try {
            param = (UIParameter) event.getComponent().findComponent("currentUserId");
            String id = param.getValue().toString();
            this.setCurrentUserId(id);
            this.currentUser = this.getUser(id);
        } catch (Exception e) {
        }
    }


    public void removeUser(ActionEvent event) {
        UIParameter param = null;
        try {
            param = (UIParameter) event.getComponent().findComponent("removeUserId");
            String id = param.getValue().toString();
            removeUserInfo = "删除" + id + "成功";
            request.removeUser(id);
            userResult = request.getAllUsers();
        } catch (Exception e) {
            removeUserInfo = e.getMessage();
        }
    }

    public String getUpdateUserInfo() {
        return updateUserInfo;
    }

    public void setUpdateUserInfo(String updateUserInfo) {
        this.updateUserInfo = updateUserInfo;
    }

    /**
     * 管理员修改
     *
     * @return
     */
    private String updateUserInfo;

    public void updateUser() {
        try {
            request.updateUser(currentUser);
            updateUserInfo = "修改成功";
            userResult=request.getAllUsers();
        } catch (Exception e) {
            updateUserInfo = "修改失败";
        }
    }


    //获取user信息


    /**
     * Order
     */
    public List<Order> getOrderbyTime(Date startTime, Date endTime) {
        try {
            return request.getOrderbyTime(startTime, endTime);
        } catch (EJBException e) {
            throw e;
        }
    }

    /**
     * Dish
     */
    public List<Dish> getDishesbyType(String type) {
        try {
            return request.getDishesbyType(type);
        } catch (EJBException e) {
            throw e;
        }
    }


    public void setAllDishes() {
        try {
            this.allDishes = request.getAllDishes();
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Dish> getAllDishes() {
        return allDishes;
    }

    /**
     * DishinOrder
     */

    public List<Dish> getDishsbyOrder(Integer orderId) {
        try {
            return request.getDishesbyOrder(orderId);
        } catch (EJBException e) {
            throw e;
        }
    }

    public List<String> getOrdersbyDish(String dishId) {
        try {
            return request.getOrdersbyDish(Integer.parseInt(dishId));
        } catch (EJBException e) {
            throw e;
        }
    }


    private String userSearchKey;

    public String getUserSearchKey() {
        return userSearchKey;
    }

    public void setUserSearchKey(String userSearchKey) {
        this.userSearchKey = userSearchKey;
    }

    public void getUserbyTelNumber() {
        userResult.clear();
        userResult.add(request.getUserbyTel(userSearchKey));
    }

    public List<User> getUserResult() {
        if (userResult == null) {
            userResult = request.getAllUsers();
        }
        return userResult;
    }

    public void setUserResult(List<User> userResult) {
        this.userResult = userResult;
    }

    private List<User> userResult;


    public List<User> getAllUsers() {
        try {
            return request.getAllUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * bill
     */
    public List<Bill> getBillbyDate(Date startTime, Date endTime) {
        try {
            return request.getBillbyDate(startTime, endTime);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Bill> getBillbyType(boolean type) {
        try {
            return request.getBillsbyType(type);
        } catch (Exception e) {
            throw e;
        }
    }

    public Bill getBillbyId(Integer itemId) {
        try {
            return request.getBillbyId(itemId);
        } catch (Exception e) {
            throw e;
        }
    }
//---------------点餐-------------


    /**
     * 返回所有以点菜品
     */
    public List<Dish> getAllDishesNow() {
        return dishes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public String getCustomerTelNumber() {
        return customerTelNumber;
    }

    public String getComment() {
        return comment;
    }

    public Integer getCurrentOrder() {
        return currentOrder;
    }

    public String getCurrentDish() {
        return currentDish;
    }

    public String getDiscount() {
        return discount;
    }

    public Integer getCurrentDishId() {
        return currentDishId;
    }

    public void setCurrentDishId(Integer currentDishId) {
        this.currentDishId = currentDishId;


    }

    /**
     * 添加菜品
     */

    private Integer currentDishId;
    private String addDishInfo;

    public String getAddDishInfo() {
        return addDishInfo;
    }

    public void setAddDishInfo(String addDishInfo) {
        this.addDishInfo = addDishInfo;
    }


    private LinkedList<Dish> dishesHad;

    public LinkedList<Dish> getDishesHad() {
        return dishesHad;
    }

    public void setDishesHad(LinkedList<Dish> dishesHad) {
        this.dishesHad = dishesHad;
    }

    public void addDish(ActionEvent event) {

        if (dishesHad == null) {
            dishesHad = new LinkedList<>();
//            addDishInfo="重新创建";
        }

        try {
            UIComponent com = event.getComponent();
            String id = ((UIParameter) com.findComponent("addDishId")).getValue().toString();
            Dish dish = (Dish) request.getDishbyId(Integer.parseInt(id));
//            addDishInfo=dishes.isEmpty()+"";
            dishesHad.push(dish);
//            addDishInfo="添加成功";
            addDishInfo=dishesHad.isEmpty()+"";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            addDishInfo="出错啦";
        }

    }

    /**
     * 删除菜品
     */
    public void removeDish(Dish dish) {
        for (int i = this.dishesHad.size() - 1; i >= 0; i--) {
            Dish item = this.dishesHad.get(i);
            if (dish.equals(item)) {
                this.dishesHad.remove(item);
            }
        }
    }

    public String getDishInfo() {
        return dishInfo;
    }

    public void setDishInfo(String dishInfo) {
        this.dishInfo = dishInfo;
    }

    /**
     * 创建订单
     */
    public void newOrder(Seat seat,User user) {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        int orderPrice = 0;
        String status;
        if(!dishesHad.isEmpty()){
            for (int i =this.dishesHad.size()-1;i>0;i--) {
                orderPrice += Integer.parseInt(this.dishesHad.get(i).getDishPrice());
            }
            try {
                Customer customer = request.getCustomerbyTelNumber(customerTelNumber);
                request.createOrder(String.valueOf(orderPrice), Integer.parseInt(this.discount), this.comment, seat, user, customer, dishesHad);
            } catch (Exception e) {
                request.createOrder(String.valueOf(orderPrice), Integer.parseInt(this.discount), this.comment, seat, user, null, dishesHad);
                throw e;
            }
            this.dishInfo="添加成功";
        }
        else{
            this.dishInfo="未添加菜品";
        }

    }
//-------------------------------
}
