package edu.geekhub;

import com.google.gson.Gson;
import edu.geekhub.bucket.Order;
import edu.geekhub.bucket.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping()
    public String allOrders() {
        System.out.println(orderService.getOrders());
        return new Gson().toJson(orderService.getOrders());
    }


    @PostMapping()
    public String create(@ModelAttribute("order") Order order) {
        orderService.addOrder(order);
        return "orders";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        orderService.deleteOrder(id);
        return "orders";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("order") Order order, @PathVariable("id") int id){
        orderService.update(id,order);
        return "orders";
    }


}
