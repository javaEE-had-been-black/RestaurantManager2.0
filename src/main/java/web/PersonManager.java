package web;

import ejb.RequestBean;
import entity.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author zhang
 */
@Named
@SessionScoped
@SuppressWarnings("unused")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class PersonManager implements Serializable {
    @EJB
    private RequestBean request;
    private static final Logger logger = Logger.getLogger("RestaurantManager.web.PersonManager");
    private User user;
    private String userId;
    private String password;
    private String logInfo;
    private String updateInfo;


    /**
     * @param
     * @return 是否运行登录
     */
    public String login() {
        try {

            if (userId == null || password == null) {
                logInfo = "请输入账号或密码";
                return "fail";
            } else if (password.equals(request.getUserbyUserId(userId).getPassword())) {
                user = request.getUserbyUserId(userId);
                logInfo = "";
                return "success";
            } else {
                logInfo = "密码错误或账号不存在";
                return "fail";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logInfo = "账号不存在";
            return "fail";
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 得到个人信息
     *
     * @return
     */

    public User getUser() {
        return user;
    }

    public String getLogInfo() {
        return logInfo;
    }

    /**
     * 更新个人信息
     *
     * @param password
     * @param telNumber
     */

    public void updateUserInfo(String password,
                               String telNumber) {
        if (telNumber.length() != 11) {
            updateInfo = "请输入正确位数的电话号码！";
        } else {
            try {
                user.setPassword(password);
                user.setTelNumber(telNumber);
                request.updateUser(user);
                updateInfo = "修改成功!";
            } catch (Exception e) {
                updateInfo = "修改失败!";
            }
        }
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

}