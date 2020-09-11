package web;

import ejb.RequestBean;
import entity.*;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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

    /**
     * 添加user
     */
    public void createUser() {
        try {
            request.createUser(newTelNumber, newUserName, newPassword, newPosition, newTelNumber, newSalary);
        } catch (Exception e) {
            logger.warning("Create User Failed,the reason is" + e.getMessage());
            throw e;
        }
    }

    /**
     * 删除user
     */
    public void removeUser(String currentUserId) {
        try {
            request.removeUser(currentUserId);
        } catch (Exception e) {
            logger.warning("Remove User Failed,the reason is" + e.toString());
        }
    }

    /**
     * 管理员修改
     *
     * @param userId
     * @param userName
     * @param password
     * @param position
     * @param telNumber
     * @param salary
     * @return
     */
    public String updateUserInfo(String userId,
                                 String userName,
                                 String password,
                                 String position,
                                 String telNumber,
                                 String salary) {
        User user = request.getUserbyUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        user.setPosition(position);
        user.setTelNumber(telNumber);
        user.setSalary(salary);
        request.updateUser(user);
        return "success";
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

    /**
     * 添加菜品
     */
    public void addDishes(Dish dish) {
        dishes.add(dish);
    }

    /**
     * 删除菜品
     */
    public void removeDish(Dish dish) {
        for (int i = this.dishes.size() - 1; i >= 0; i--) {
            Dish item = this.dishes.get(i);
            if (dish.equals(item)) {
                this.dishes.remove(item);
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
    public void newOrder(String seatId, String userId) {
//        SimpleDateFormat sdf = new SimpleDateFormat();
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        int orderPrice = 0;
        String status;
        if(this.dishes!=null){
            for (int i =this.dishes.size()-1;i>0;i--) {
                orderPrice += Integer.parseInt(this.dishes.get(i).getDishPrice());
            }
            try {
                Seat seat = request.getSeatbySeatId(seatId);
                User user = request.getUserbyUserId(userId);
                Customer customer = request.getCustomerbyTelNumber(customerTelNumber);
                request.createOrder(String.valueOf(orderPrice), Integer.parseInt(this.discount), this.comment, seat, user, customer, dishes);
                this.dishes.clear();
            } catch (Exception e) {
                Seat seat = request.getSeatbySeatId(seatId);
                User user = request.getUserbyUserId(userId);
                request.createOrder(String.valueOf(orderPrice), Integer.parseInt(this.discount), this.comment, seat, user, null, dishes);
                throw e;
            }
        }
        else{
            this.dishInfo="未添加菜品";
        }

    }
//-------------------------------
}
