package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhang
 */
@Entity
@Table(name = "RESTAURANT_ORDER")
@NamedQueries(
        {
                @NamedQuery(
                        name = "getOrderbyOrderId",
                        query = "SELECT o FROM Order o WHERE o.orderId=:orderId"
                ),
                @NamedQuery(
                        name = "getDishesbyOrder",
                        query = "SELECT o.dishes FROM Order o WHERE o.orderId=:orderId"
                ),
                @NamedQuery(
                        name = "getOrderIdbySeatIdandStatus",
                        query = "SELECT o.orderId FROM Order o WHERE o.seat.seatId=:seatId and o.orderStatus=:orderStatus"
                )
        }
)

public class Order implements Serializable {
    private Integer orderId;
    private Date dateTime;
    private String orderPrice;
    private Integer discount;
    private String comment;
    private String orderStatus;
    private Seat seat;
    private User user;
    private Customer customer;
    private List<Dish> dishes;

    @ManyToMany()
    @JoinTable(name = "RESTAURANT_DISHINORDER", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "orderid"),
            inverseJoinColumns = @JoinColumn(name = "dishId", referencedColumnName = "dishId"))
    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Order() {
    }

    public Order(String orderPrice, Integer discount, String comment, Seat seat, User user, Customer customer, List<Dish> dishes) {
        this.dateTime = new Date();
        this.orderPrice = orderPrice;
        this.discount = discount;
        this.comment = comment;
        this.seat = seat;
        this.user = user;
        this.customer = customer;
        this.dishes = dishes;
    }

    @ManyToOne
    @JoinColumn(
            name = "seatId"
    )
    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @ManyToOne
    @JoinColumn(
            name = "userId"
    )
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(
            name = "customerId"
    )
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getOrderId() {
        return orderId;
    }

}
