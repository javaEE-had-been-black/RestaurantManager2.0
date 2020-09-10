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
 * @author zhao chenyang
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

    public void updateUser(String userId) {
        try {
            this.user = request.getUserbyUserId(userId);
        } catch (Exception e) {
            logger.warning("Update user fail.");
        }
    }

    public User getUser() {
        return user;
    }

    public void updateUserInfo(String password,
                               String telNumber) {
        try {
            user.setPassword(password);
            user.setTelNumber(telNumber);
            this.updateInfo = "success";
        } catch (Exception e) {
            this.updateInfo = "fail";
        }
    }
}
