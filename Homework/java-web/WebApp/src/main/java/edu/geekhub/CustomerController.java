package edu.geekhub;

import com.google.gson.Gson;
import edu.geekhub.customer.Customer;
import edu.geekhub.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping()
    public String allCustomers() {
        return new Gson().toJson(customerService.getCustomers());
    }

    @GetMapping("customers/{id}")
    public String customerById(@PathVariable("id") Integer id) {
        return new Gson().toJson(customerService.getCustomerById(id));
    }

    @PostMapping()
    public String create(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return "customers";
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") Integer id){
        customerService.deleteCustomer(id);
        return true;
    }

    @PutMapping("customers/{id}")
    public boolean update(@RequestBody Customer customer){
        customerService.update(customer.getId(), customer);
        return true;
    }
}