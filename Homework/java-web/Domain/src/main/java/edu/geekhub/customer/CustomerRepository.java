package edu.geekhub.customer;

import edu.geekhub.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer getCustomerById(int id){
        for (Customer customer: customers) {
            if(customer.getId()==id)
                return customer;
        }
        return null;
    }

    public void deleteCustomer(int id) {
        customers.removeIf(customer ->
                customer.getId() == id
        );
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
