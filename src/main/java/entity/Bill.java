package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author zhang
 */
@Entity
@Table(name = "RESTAURANT_BILL")
@NamedQueries({
        @NamedQuery(
                name = "getBillsbyDate",
                query = "SELECT b FROM Bill b WHERE b.itemDate>:startTime and b.itemDate<:endTime"
        ),
        @NamedQuery(
                name = "getBillsbyType",
                query = "SELECT b FROM Bill b WHERE b.type=:type"
        ),
        @NamedQuery(
                name = "getBillbyId",
                query = "SELECT b FROM Bill b WHERE b.itemId=:itemId"
        ),
        @NamedQuery(
                name = "getAllBills",
                query = "SELECT b FROM Bill b"
        )
})
public class Bill implements Serializable {
    private Integer itemId;
    private Date itemDate;
    private String commit;

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    boolean type;
    String amount;

    public Bill(boolean type, String amount,String commit) {
        this.commit=commit;
        this.itemDate = new Date();
        this.type = type;
        this.amount = amount;
    }

    public Bill() {

    }

    public boolean isType() {
        return type;
    }

    @Temporal(TIMESTAMP)
    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getItemId() {
        return itemId;
    }

    public String turnBill(){
        if(this.type){
            return "收入";
        }
        return "支出";
    }
}
