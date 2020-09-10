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


    private String updateInfo;

    /**
     * 得到user信息，在界面跳转时使用
     *
     * @param userId
     */

    public void updateUser(String userId) {
        try {
            this.user = request.getUserbyUserId(userId);
        } catch (Exception e) {
            logger.warning("Update user fail.");
        }
    }

    /**
     * 得到个人信息
     *
     * @return
     */

    public User getUser() {
        return user;
    }

    /**
     * 更新个人信息
     *
     * @param password
     * @param telNumber
     */

    public void updateUserInfo(String password,
                               String telNumber) {

        try {
            user.setPassword(password);
            user.setTelNumber(telNumber);
            updateInfo = "success";
        } catch (Exception e) {
            updateInfo = "fail";
        }
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

}