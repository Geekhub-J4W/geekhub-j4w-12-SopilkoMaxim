package edu.geekhub.bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class OrderService {
    @Autowired
    OrderRepository repository;
    private static final Logger logger = Logger.getLogger(edu.geekhub.customer.Customer.class.getName());

    public void addOrder(Order order)
        {
            logger.info("Order for customer:" + order.getId_customer() + ", id product:" +
                    order.getId_product() + ", quantity = " + order.getQuantity_products()
                    + " was added to Database");
            repository.addOrder(order);
        }

    public void deleteOrder(int id) {

            logger.info("Order with id" + id + " was deleted");
            repository.deleteOrder(id);
        }

    public Order getOrderById(int id)
    {
        return repository.getOrderById(id);
    }
    public List<Order> getOrders(){
        return repository.getOrders();
    }

    public void update(int id, Order order) {
        logger.info("Order with id" + id + " was updated");
        repository.update(id,order);
    }
    }

