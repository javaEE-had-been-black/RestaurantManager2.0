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
    private Repository item;
    private String searchKey;
    private List<Repository> allItems;

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    /**
     * 获取仓库所有item实体
     *
     * @return 仓库中所有实体的List
     */
    public void setAllItems() {
        try {
            this.allItems = request.getAllItems();
            if (this.allItems.isEmpty()) {
                repositoryInfo = "null";
            }
            repositoryInfo = null;

        } catch (Exception e) {
            logger.warning("Get items fail, the reason is" + e.getMessage());
            repositoryInfo = "读取数据库出错";
            throw e;
        }
    }

    public List<Repository> getAllItems() {
        return this.allItems;
    }

    public void setItem(Repository item) {
        this.item = item;
    }

    /**
     * 更改条目数量，负数给提示信息quantityInfo="数量不能为负"
     *
     * @param item
     */
    public void updateItem(Repository item) {
        if (Integer.parseInt(item.getQuantity()) < 0) {
            itemInfo = "数量不能为负";
        } else {
            try {
                this.item = item;
                request.updateItem(this.item);
                itemInfo = "更新成功!";
            } catch (Exception e) {
                itemInfo = "更新失败!";
            }
        }
    }


    public String getItemInfo() {
        return itemInfo;
    }


    /**
     * 根据类型得到仓库条目
     *
     * @param itemType
     * @return
     */
    public void getItemsbyType(String itemType) {
        if ("全部".equals(itemType)) {
            try {
                this.setAllItems();
                if (this.allItems.isEmpty()) {
                    itemInfo = "null";
                }
            } catch (Exception e) {
                itemInfo = "null";
            }
        }else {
            try {
                this.allItems = request.getItemsbyType(itemType);
                if (this.allItems.isEmpty()) {
                    itemInfo = "null";
                }
            } catch (Exception e) {
                itemInfo = "获取信息失败，请检查输入是否正确";
            }
        }
    }

    public void removeRepository(Integer itemId) {
        try {
            request.removeRepository(itemId);
            itemInfo = "删除成功";
        } catch (Exception e) {
            itemInfo = "删除失败";
        }
    }
}
