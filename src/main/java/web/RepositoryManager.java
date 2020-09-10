package web;

import ejb.RequestBean;
import entity.Repository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author zhang
 */
@Named
@SessionScoped
@SuppressWarnings("unused")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class RepositoryManager implements Serializable {
    @EJB
    private RequestBean request;
    private static final Logger logger = Logger.getLogger("RestaurantManager.web.RepositoryManager");
    private String itemInfo;
    private String repositoryInfo;

    /**
     * 获取仓库所有item实体
     *
     * @return 仓库中所有实体的List
     */
    public List<Repository> getAllItems() {
        try {
            List<Repository> items = request.getAllItems();
            if (items.isEmpty()) {
                repositoryInfo = "null";
                return null;
            }
            repositoryInfo = null;
            return items;
        } catch (Exception e) {
            logger.warning("Get items fail, the reason is" + e.getMessage());
            repositoryInfo = "读取数据库出错";
            throw e;
        }
    }

    /**
     * 更改条目数量，负数给提示信息quantityInfo="数量不能为负"
     * @param item
     * @param quantity
     */
    public void updateQuantity(Repository item, String quantity) {
        if (Integer.parseInt(quantity) < 0) {
            itemInfo = "数量不能为负";
        } else {
            try {
                item.setQuantity(quantity);
            } catch (Exception e) {
                itemInfo = "设置失败";
            }
        }
    }

    /**
     * 更改条目名称
     * @param item
     * @param itemName
     */
    public void updateItemName(Repository item, String itemName) {
        try {
            item.setItemName(itemName);
        } catch (Exception e) {
            itemInfo = "设置失败";
        }
    }

    /**
     * 更改条目类型
     * @param item
     * @param itemType
     */
    public void updateItemType(Repository item, String itemType) {
        try {
            item.setType(itemType);
        } catch (Exception e) {
            itemInfo = "设置失败";
        }
    }

    /**
     * 根据类型得到仓库条目
     * @param itemType
     * @return
     */
    List<Repository> getItembyType(String itemType){
        try{
            List<Repository> items= request.getItemsbyType(itemType);
            if(items.equals(0)){
                itemInfo="null";
                return null;
            }
            return items;
        }catch (Exception e){
            itemInfo="获取信息失败，请检查输入是否正确";
            return null;
        }
    }
}
