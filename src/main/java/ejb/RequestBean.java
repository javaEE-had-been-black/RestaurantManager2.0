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
    *  Creator
    */
    public void createUser(String userId,
                           String userName,
                           String password,
                           String position,
                           String telNumber,
                           String salary){
        try{
            User user = new User(userId,userName,password,position,telNumber,salary);
            logger.log(Level.INFO,"Create user!!!!!!!!!!!",new Object[]{userName,password});
            em.persist(user);
            logger.log(Level.INFO,"Persisted user!!!!!",new Object[]{userName,password});

        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    };
    public void createSeat(String seatId,
                    String capacity,
                    String status,
                    boolean isPrivate){
        try{
            Seat seat = new Seat(seatId,capacity,status,isPrivate);
            em.persist(seat);

        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public void createDish(String dishId,
                           String dishName,
                           String dishPrice,
                           String imageUrl,
                           String type){
        try{
            Dish dish = new Dish(dishId,dishName,dishPrice,imageUrl,type);
            em.persist(dish);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public void createOrder(Integer orderId,
                            Date startTime,
                            Date endTime,
                            String orderPrice,
                            Integer discount,
                            String comment,
                            Seat seat,
                            User user,
                            Customer customer){
        try{
            Order order = new Order(orderId,startTime,endTime,orderPrice,discount,
                                    comment,seat,user,customer
                                    );
            em.persist(order);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public void createCustomer(String telNumber,
                               String customerName){
        try{
            Customer customer = new Customer(telNumber,customerName);
            em.persist(customer);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public void createBill(Integer itemId,
                           Date itemDate,
                           boolean type,
                           String amount){
        try{
            Bill bill = new Bill(itemId,itemDate,type,amount);
            em.persist(bill);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public void createRepository(Integer itemId,
                                 String itemName,
                                 String quantity,
                                 String type){
        try{
            Repository repository = new Repository(itemId,itemName,quantity,type);
            em.persist(repository);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * User
     */
    public User getUser(String userId){
        try{
            return (User) em.createNamedQuery("getUserbyUserId")
                    .setParameter("userId",userId)
                    .getSingleResult();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public User searchUserIdbytel(String telNumber){
        try{
            return (User)em.createNamedQuery("searchUserbyTel")
                    .setParameter("telNumber",telNumber)
                    .getSingleResult();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Seat
     */
    public List<Seat> getSeatsbyCapacity(String capacity){
        try{
            return em.createNamedQuery("getSeatsbyCapacity")
                    .setParameter("capacity",capacity)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public List<Seat> getAllPrivateSeats(){
        //获得所有包厢
        try{
            return em.createNamedQuery("getAllPrivateSeats")
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public String getSeatStatus(String seatId){
        try{
            return (String) em.createNamedQuery("getSeatStatus")
                    .setParameter("seatId",seatId)
                    .getSingleResult();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Dish
     */
    public List<Dish> getDishbyType(String type){
        try{
            return em.createNamedQuery("getDishesbyType")
                    .setParameter("type",type)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Order
     */
    public List<Order> getOrderbyTime(Date startTime,Date endTime){
        try{
            return em.createNamedQuery("getOrdersbyTime")
                    .setParameter("startTime",startTime)
                    .setParameter("endTime",endTime)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Dish_in_Order
     */
    public List<String> getDishsbyOrder(Integer orderId){
        try{
            return em.createNamedQuery("getDishesbyOrder")
                    .setParameter("orderId",orderId)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public List<String> getOrdersbyDish(String dishId){
        try{
            return em.createNamedQuery("getOrdersbyDish")
                    .setParameter("dishId",dishId)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Customer
     */
    public void addPoints(Integer customerId,Integer points){
        try{
            em.createNamedQuery("addPoints")
                    .setParameter("customerId",customerId)
                    .setParameter("points",points);
        }catch(Exception e)
        {
            throw new EJBException(e.getMessage());
        }
    }
    public void usePoints(Integer customerId,Integer points){
        try{
            em.createNamedQuery("usePoints")
                    .setParameter("customerId",customerId)
                    .setParameter("points",points);
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public List<Customer> getCustomerbyName(String customerName){
        try{
            return em.createNamedQuery("getCustomerbyName")
                    .setParameter("customerName",customerName)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * bill
     */
    public List<Bill> getBillbyDate(Date startTime,Date endTime){
        try{
            return em.createNamedQuery("getBillsbyDate")
                    .setParameter("startTime",startTime)
                    .setParameter("endTime",endTime)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public List<Bill> getBillbyType(boolean type){
        try{
            return em.createNamedQuery("getBillsbyType")
                    .setParameter("type",type)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public Bill getBillbyId(Integer itemId){
        try{
            return (Bill) em.createNamedQuery("getBillbyId")
                    .setParameter("itemId",itemId)
                    .getSingleResult();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }

    /**
     * Repository
     */
    public List<Repository> getItemsbyName(String itemName){
        try{
            return em.createNamedQuery("getItemsbyName")
                    .setParameter("itemName",itemName)
                    .getResultList();
        }catch(Exception e){
            throw new EJBException(e.getMessage());
        }
    }
    public List<Repository> getAllItems(){
        try {
            return em.createNamedQuery("getAllItems")
                    .getResultList();
        }catch (Exception e){
            throw new EJBException(e.getMessage());
        }
    }
}
