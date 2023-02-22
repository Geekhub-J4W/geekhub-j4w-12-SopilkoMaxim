package edu.geekhub.customer;

import edu.geekhub.product.Product;
import edu.geekhub.product.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
@Component
public class CustomerService {
    private static final Logger logger = Logger.getLogger(edu.geekhub.customer.Customer.class.getName());
    @Autowired
    private final CustomerRepository repository;
    private Scanner scan =new Scanner(System.in);

    @PostConstruct
    public void initBD(){
        repository.addCustomer(new Customer("Tonny",20));
        repository.addCustomer(new Customer("Locky",25));
        repository.addCustomer(new Customer("Stive",30));
        repository.addCustomer(new Customer("Tchala",60));
        repository.addCustomer(new Customer("Tor",40));
    }
    public CustomerService(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        if (CustomerValidator.validateName(customer, logger) && CustomerValidator.validateAge(customer, logger)) {
            logger.info("Customer " + customer.getName() + " with Id " + customer.getId() + " with age "
                    + customer.getAge() + " was added to Database");
            repository.addCustomer(customer);
        }
    }

    public void deleteCustomer(int id) {
        if(CustomerValidator.validateIdExist(id,repository,logger)) {
            Customer customer = repository.getCustomerById(id);
            logger.info("Customer " + customer.getName() + " with Id " + customer.getId() + " with age "
                    + customer.getAge() + " was deleted from Database");
            repository.deleteCustomer(id);
        }
    }

    public Customer getCustomerById(int id)
    {
        if(CustomerValidator.validateIdExist(id,repository,logger))
            return repository.getCustomerById(id);
        else {
            System.out.println("Wrong Id try again");
            int newId=scan.nextInt();
            return this.getCustomerById(newId);
        }
    }

    public Customer getCustomerByName(String name)
    {
        for (Customer customer:repository.getCustomers()) {
            if(customer.getName()==name)
                return customer;
        }
        System.out.println("Wrong Name try again");
        name=scan.nextLine();
        return this.getCustomerByName(name);
    }

    public List<Customer> getCustomers() {
        return repository.getCustomers();
    }
}
