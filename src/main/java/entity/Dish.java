package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author zhang
 */
@Entity
@Table(name = "RESTAURANT_DISH")
@NamedQueries({
        @NamedQuery(
                name = "getDishbyId",
                query = "SELECT d FROM Dish d WHERE d.dishId=:dishId"
        ),
        @NamedQuery(
                name = "getDishesbyType",
                query = "SELECT d FROM Dish d WHERE d.type=:type"
        ),
        @NamedQuery(
                name = "getAllDishes",
                query = "SELECT d from Dish d"
        ),
        @NamedQuery(
                name = "getOrdersbyDish",
                query = "SELECT d.orders FROM Dish d WHERE d.dishId=:dishId"
        ),
        @NamedQuery(
                name = "getDishesbyDishNameandType",
                query = "SELECT d from Dish d WHERE d.dishName=:dishName and d.type=:type"
        ),
        @NamedQuery(
                name = "getDishbyName",
                query = "SELECT d from Dish d WHERE d.dishName=:dishName"
        )
})


public class Dish implements Serializable {
    private Integer dishId;
    private String dishName;
    private String dishPrice;
    private String imageUrl;
    private String type;
    private List<Order> orders;

    @ManyToMany(mappedBy = "dishes")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Dish() {
    }

    public Dish(Integer dishId, String dishName, String dishPrice, String imageUrl, String type) {
        this.dishId = dishId;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getDishId() {
        return dishId;
    }
}
