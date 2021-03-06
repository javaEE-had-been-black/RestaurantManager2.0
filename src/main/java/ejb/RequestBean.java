package ejb;

import entity.*;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author zhao chenyang
 */
@Stateful
public class RequestBean {
    @PersistenceContext
    private EntityManager em;

    private static final Logger logger = Logger.getLogger("java.ejb.RequestBean");


    /**
     * 更新User
     */
    public void updateUser(User user) {
        try {
            em.merge(user);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * 更新仓库item
     */

    public void updateItem(Repository item) {
        try {
            em.merge(item);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }


    public void updateSeat(Seat seat) {
        try {
            em.merge(seat);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void updateDish(Dish dish) {
        try {
            em.merge(dish);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            em.merge(customer);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }

    }

    /**
     * Creator
     */
    public void createUser(String userId,
                           String userName,
                           String password,
                           String position,
                           String telNumber,
                           String salary) {
        try {
            User user = new User(userId, userName, password, position, telNumber, salary);
            logger.log(Level.INFO, "Create user!!!!!!!!!!!", new Object[]{userName, password});
            em.persist(user);
            logger.log(Level.INFO, "Persisted user!!!!!", new Object[]{userName, password});

        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    ;

    public void createSeat(String seatId,
                           String capacity,
                           String status,
                           boolean isPrivate) {
        try {
            Seat seat = new Seat(seatId, capacity, status, isPrivate);
            em.persist(seat);

        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createDish(Integer dishId,
                           String dishName,
                           String dishPrice,
                           String imageUrl,
                           String type) {
        try {
            Dish dish = new Dish(dishId, dishName, dishPrice, imageUrl, type);
            em.persist(dish);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createOrder(
            String orderPrice,
            Integer discount,
            String comment,
            Seat seat,
            User user,
            Customer customer,
            List<Dish> dishes) {
        try {
            Order order = new Order(orderPrice, discount,
                    comment, seat, user, customer, dishes
            );
            em.persist(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createCustomer(String telNumber,
                               String customerName, Integer points) {
        try {
            Customer customer = new Customer(telNumber, customerName, points);
            em.persist(customer);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createBill(
            boolean type,
            String amount,
            String commit) {
        try {
            Bill bill = new Bill(type, amount, commit);
            em.persist(bill);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void createRepository(Integer itemId,
                                 String itemName,
                                 String quantity,
                                 String type) {
        try {
            Repository repository = new Repository(itemId, itemName, quantity, type);
            em.persist(repository);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * destructor
     */
    public void removeUser(String userId) {
        try {
            User user = em.find(User.class, userId);
            em.remove(user);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeSeat(String seatId) {
        try {
            Seat seat = em.find(Seat.class, seatId);
            em.remove(seat);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeDish(Integer dishId) {
        try {
            Dish dish = em.find(Dish.class, dishId);
            em.remove(dish);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeCustomer(Integer customerId) {
        try {
            Customer customer = em.find(Customer.class, customerId);
            em.remove(customer);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeOrder(Integer orderId) {
        try {
            Order order = em.find(Order.class, orderId);
            em.remove(order);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeBill(Integer itemId) {
        try {
            Bill bill = em.find(Bill.class, itemId);
            em.remove(bill);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void removeRepository(Integer itemId) {
        try {
            Repository repository = em.find(Repository.class, itemId);
            em.remove(repository);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }


    /**
     * User
     */

    public User getUserbyUserId(String userId) {
        try {
            return (User) em.createNamedQuery("getUserbyUserId")
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public User getUserbyTel(String telNumber) {
        try {
            return (User) em.createNamedQuery("getUserbyTel")
                    .setParameter("telNumber", telNumber)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<User> getUsersbyPosition(String position) {
        try {
            return em.createNamedQuery("getUsersbyPosition")
                    .setParameter("position", position)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<User> getUsersbyUserName(String userName) {
        try {
            return em.createNamedQuery("getUsersbyUserName")
                    .setParameter("userName", userName)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            return em.createNamedQuery("getAllUsers")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Seat
     */
    public List<Seat> getSeatsbyCapacity(String capacity) {
        try {
            return em.createNamedQuery("getSeatsbyCapacity")
                    .setParameter("capacity", capacity)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Seat> getAllPrivateSeats() {
        //获得所有包厢
        try {
            return em.createNamedQuery("getAllPrivateSeats")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public String getSeatStatus(String seatId) {
        try {
            return (String) em.createNamedQuery("getSeatStatus")
                    .setParameter("seatId", seatId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Integer getOrderIdbySeatIdandStatus(String seatId, String orderStatus) {
        try {
            return ((Order) em.createNamedQuery("getOrderIdbySeatIdandStatus")
                    .setParameter("seatId", seatId)
                    .setParameter("orderStatus", orderStatus)
                    .getSingleResult()).getOrderId();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Seat> getAllSeats() {
        try {
            return em.createNamedQuery("getAllSeats")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Seat getSeatbySeatId(String seatId) {
        try {
            return em.find(Seat.class, seatId);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Seat> getSeatsbyStatus(String status) {
        try {
            return em.createNamedQuery("getSeatsbyStatus")
                    .setParameter("status", status)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Dish
     */
    public List<Dish> getDishesbyType(String type) {
        try {
            return em.createNamedQuery("getDishesbyType")
                    .setParameter("type", type)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Dish getDishbyId(Integer dishId) {
        try {
            return (Dish) em.createNamedQuery("getDishbyId")
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Dish> getAllDishes() {
        try {
            return em.createNamedQuery("getAllDishes")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Dish> getDishesbyOrder(Integer orderId) {
        try {
            return em.createNamedQuery("getDishesbyOrder")
                    .setParameter("orderId", orderId)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Dish> getDishesbyDishNameandType(String dishName, String type) {
        try {
            return em.createNamedQuery("getDishesbyDishNameandType")
                    .setParameter("dishName", dishName)
                    .setParameter("type", type)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Dish getDishbyName(String dishName) {
        try {
            return (Dish) em.createNamedQuery("getDishbyName")
                    .setParameter("dishName", dishName)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Order
     */
    public List<Order> getOrderbyTime(Date startTime, Date endTime) {
        try {
            return em.createNamedQuery("getOrdersbyTime")
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<String> getOrdersbyDish(Integer dishId) {
        try {
            return em.createNamedQuery("getOrdersbyDish")
                    .setParameter("dishId", dishId)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Order getOrderbyOrderId(Integer orderId) {
        try {
            return (Order) em.createNamedQuery("getOrderbyOrderId")
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Customer
     */
    public void addPoints(Integer customerId, Integer points) {
        try {
            em.createNamedQuery("addPoints")
                    .setParameter("customerId", customerId)
                    .setParameter("points", points);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void usePoints(Integer customerId, Integer points) {
        try {
            em.createNamedQuery("usePoints")
                    .setParameter("customerId", customerId)
                    .setParameter("points", points);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Customer getCustomerbyTelNumber(String telNumber) {
        try {
            return (Customer) em.createNamedQuery("getCustomerbyTelNumber")
                    .setParameter("telNumber", telNumber)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Customer> getCustomerbyCustomerName(String customerName) {
        try {
            return em.createNamedQuery("getCustomerbyCustomerName")
                    .setParameter("customerName", customerName).getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Customer getCustomerbyCustomerId(Integer customerId) {
        try {
            return (Customer) em.createNamedQuery("getCustomerbyCustomerId")
                    .setParameter("customerId", customerId);
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Customer> getAllCustomers() {
        try {
            return em.createNamedQuery("getAllCustomers")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Customer> getCustomersbyDate(Date startTime, Date endTime) {
        try {
            return em.createNamedQuery("getCustomersbyDate")
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * bill
     */
    public List<Bill> getBillbyDate(Date startTime, Date endTime) {
        try {
            return em.createNamedQuery("getBillsbyDate")
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Bill> getBillsbyType(boolean type) {
        try {
            return em.createNamedQuery("getBillsbyType")
                    .setParameter("type", type)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public Bill getBillbyId(Integer itemId) {
        try {
            return (Bill) em.createNamedQuery("getBillbyId")
                    .setParameter("itemId", itemId)
                    .getSingleResult();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Bill> getAllBills() {
        try {
            return em.createNamedQuery("getAllBills")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Repository
     */
    public List<Repository> getItemsbyName(String itemName) {
        try {
            return em.createNamedQuery("getItemsbyName")
                    .setParameter("itemName", itemName)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * 根据类型得到仓库条目
     */

    public List<Repository> getItemsbyType(String type) {
        try {
            return em.createNamedQuery("getItemsbyType")
                    .setParameter("type", type)
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

    public List<Repository> getAllItems() {
        try {
            return em.createNamedQuery("getAllItems")
                    .getResultList();
        } catch (Exception e) {
            throw new EJBException(e.getMessage());
        }
    }

}
