package web;

import ejb.RequestBean;
import entity.Bill;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhao chenyang
 */
@Named
@SessionScoped
@SuppressWarnings("unused")
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class BillManager implements Serializable {
    @EJB
    private RequestBean request;
    private String billInfo;
    private String searchKey;
    private String amount;
    private String type;
    private Integer billId;
    private List<Bill> allBills;
    private List<Bill> typeBills;

    public List<Bill> getTypeBills() {
        return typeBills;
    }

    public void setTypeBills(String type) {
        try {
            boolean value;
            value = "收入".equals(type);
            this.typeBills = request.getBillsbyType(value);
            billInfo = null;
            if (this.typeBills.isEmpty()) {
                billInfo = "null";
            }
        } catch (Exception e) {
            billInfo = "null";
            throw e;
        }
    }

    public List<Bill> getAllBills() {
        return this.allBills;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(String billInfo) {
        this.billInfo = billInfo;
    }

    public void setAllBills() {
        try {
            this.allBills = request.getAllBills();
            billInfo = null;
            if (this.allBills.isEmpty()) {
                billInfo = "null";
            }
        } catch (Exception e) {
            billInfo = "null";
            throw e;
        }
    }

    public List<Bill> getBillsbyTime(Date startTime, Date endTime) {
        try {
            List<Bill> bills = request.getBillbyDate(startTime, endTime);
            billInfo = null;
            if (bills.isEmpty()) {
                billInfo = "null";
            }
            return bills;
        } catch (Exception e) {
            billInfo = "null";
            throw e;
        }
    }

    public List<Bill> getBillsbyType(String type) {
        try {
            if ("收入".equals(type)) {
                List<Bill> bills = request.getBillsbyType(true);
                billInfo = null;
                if (bills.isEmpty()) {
                    billInfo = "null";
                }
                return bills;
            } else {
                List<Bill> bills = request.getBillsbyType(false);
                billInfo = null;
                if (bills.isEmpty()) {
                    billInfo = "null";
                }
                return bills;
            }
        } catch (Exception e) {
            billInfo = "null";
            throw e;
        }
    }

    public Bill getBillbyBillId(Integer billId) {
        try {
            Bill bill = request.getBillbyId(billId);
            billInfo = null;
            return bill;
        } catch (Exception e) {
            billInfo = "null";
            throw e;
        }
    }

    public void createBill(String type, String amount) {
        try {
            if ("收入".equals(type)) {
                request.createBill(true, amount);
                billInfo = "成功";
            } else if ("支出".equals(type)) {
                request.createBill(false, amount);
                billInfo = "成功";
            } else {
                billInfo = "数据格式错误";
            }
        } catch (Exception e) {
            billInfo = "数据格式错误";
        }
    }

    public void removeBill(Integer billId) {
        try {
            request.removeBill(billId);
            billInfo = "删除成功";
        } catch (Exception e) {
            billInfo = "删除失败";
        }
    }
}
