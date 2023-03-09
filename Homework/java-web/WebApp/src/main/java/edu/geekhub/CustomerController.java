package edu.geekhub;

import edu.geekhub.customer.Customer;
import edu.geekhub.customer.CustomerService;
import edu.geekhub.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    public String allNotes(Model model) {
        LocalDateTime now = LocalDateTime.now();

        String formattedDateTime = DateTimeFormatter.ISO_DATE_TIME.format(now);

        model.addAttribute("dateTime", formattedDateTime);
        model.addAttribute(
                "customers",
                List.copyOf(
                        customerService.getCustomers()
                )
        );
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    @PostMapping()
    public String create(@ModelAttribute("customer") Customer customer) {
        customerService.addCustomer(customer);
        return "customers";
    }

}