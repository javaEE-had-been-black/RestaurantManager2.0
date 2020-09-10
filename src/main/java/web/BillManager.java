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

    public String getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(String billInfo) {
        this.billInfo = billInfo;
    }

    public List<Bill> getAllBills(){
        try{
            List<Bill> bills = request.getAllBills();
            billInfo = null;
            if(bills.isEmpty()){
                billInfo = "null";
            }
            return bills;
        }catch (Exception e){
            billInfo = "null";
            throw e;
        }
    }
    public List<Bill> getBillsbyTime(Date startTime, Date endTime){
        try{
            List<Bill> bills = request.getBillbyDate(startTime,endTime);
            billInfo = null;
            if(bills.isEmpty()){
                billInfo = "null";
            }
            return bills;
        }catch (Exception e){
            billInfo = "null";
            throw e;
        }
    }
    public List<Bill> getBillsbyType(boolean type){
        try{
            List<Bill> bills = request.getBillbyType(type);
            billInfo = null;
            if(bills.isEmpty()){
                billInfo = "null";
            }
            return bills;
        }catch (Exception e){
            billInfo = "null";
            throw e;
        }
    }
    public Bill getBillbyBillId(Integer billId){
        try{
            Bill bill = request.getBillbyId(billId);
            billInfo = null;
            return bill;
        }catch (Exception e){
            billInfo = "null";
            throw e;
        }
    }
}
