package edu.geekhub;

import edu.geekhub.bucket.BucketController;
import edu.geekhub.customer.Customer;
import edu.geekhub.customer.CustomerService;
import edu.geekhub.product.Product;
import edu.geekhub.product.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);


        ProductService productService = context.getBean(ProductService.class);
        CustomerService customerService = context.getBean(CustomerService.class);
        BucketController bucketController = context.getBean(BucketController.class);
        int menu = 0;
        while (menu != 4) {
            System.out.println("Menu:\n1.Product menu\n2.Customer menu\n3.Bucket menu" +
                    "\n4.Exit");
            menu = scan.nextInt();
            menu = checkMenu(menu, 4);
            switch (menu) {
                case 1 -> {
                    productMenu(productService);
                }
                case 2 -> {
                    customerMenu(customerService);
                }
                case 3 -> {
                    bucketMenu(bucketController);
                }
            }
        }

    }

    private static void bucketMenu(BucketController bucketController) {
        int bucketMenu = 0;
        int id;
        while (bucketMenu != 7) {
            System.out.println("1.Set customer by Id\n2.Set customer by Name\n3.Add product to bucket"
                    + "\n4.Delete product from bucket by Id\n5.Show products in bucket\n6.Create Order\n7.Back");
            bucketMenu = scan.nextInt();
            bucketMenu = checkMenu(bucketMenu, 7);
            switch (bucketMenu) {
                case 1 -> {
                    System.out.println("Enter Id of customer");
                    id= scan.nextInt();
                    bucketController.setCustomer(id);
                }
                case 2->{
                    System.out.println("Enter name of customer");
                    String name;
                    scan.nextLine();
                    name=scan.nextLine();
                    bucketController.setCustomer(name);

                }
                case 3->{
                    System.out.println("Enter id of product to add");
                    id= scan.nextInt();
                    System.out.println("Enter number of products");
                    int quantity = scan.nextInt();
                    while (bucketController.addProductWithQuanity(id,quantity)){
                        quantity = scan.nextInt();
                    }
                }
                case 4->{
                    System.out.println("Enter id of product to delete");
                    id= scan.nextInt();
                    bucketController.deleteProduct(id);
                }
                case 5->{
                    bucketController.showProductsInBucket();
                }
                case 6->{
                    bucketController.createOrder();
                    System.out.println("Order created");
                }
            }

        }
    }

    private static void customerMenu(CustomerService customerService) {
        int customerMenu = 0;
        while (customerMenu != 4) {
            System.out.println("1.Add customer\n2.Delete customer\3Show all\n4.Back");
            customerMenu = scan.nextInt();
            customerMenu = checkMenu(customerMenu, 4);
            switch (customerMenu) {
                case 1 -> {
                    String name;
                    int age;
                    System.out.println("Enter name of customer:");
                    scan.nextLine();
                    name = scan.nextLine();
                    System.out.println("Enter customers Age:");
                    age = scan.nextInt();
                    customerService.addCustomer(new Customer(name, age));
                }
                case 2 -> {
                    int id;
                    System.out.println("Enter customer Id which you want to delete");
                    id = scan.nextInt();
                    customerService.deleteCustomer(id);
                }
                case 3 -> {
                    System.out.println(customerService.getCustomers());
                }
            }
        }
    }

    private static void productMenu(ProductService productRepository) {
        int productMenu = 0;
        while (productMenu != 6) {
            System.out.println("Menu:\n1.Add product\n2.Delete product\n3.Show all products\n4.Sort and show by price");
            System.out.println("5.Show and sort by name\n6.Back");
            productMenu = scan.nextInt();
            productMenu = checkMenu(productMenu, 5);
            switch (productMenu) {
                case 1 -> {
                    addProduct(productRepository);
                }
                case 2 -> {
                    deleteProduct(productRepository);
                }
                case 3 -> {
                    System.out.println(productRepository.getProducts());
                }
                case 4 -> {
                    System.out.println(productRepository.sortedListByPrice());
                }
                case 5 -> {
                    System.out.println(productRepository.sortedListByName());
                }
            }
        }
    }

    private static void deleteProduct(ProductService productService) {
        int id;
        System.out.println("Enter product Id which you want to delete");
        id = scan.nextInt();
        productService.deleteProduct(id);
    }

    private static void addProduct(ProductService productService) {
        String name;
        int price;
        int quantityOnStock;
        int rating;
        System.out.println("Enter name of product:");
        scan.nextLine();
        name = scan.nextLine();
        System.out.println("Enter price");
        price = scan.nextInt();
        System.out.println("Enter quantity on stock");
        quantityOnStock=scan.nextInt();
        System.out.println("Enter rating");
        rating=scan.nextInt();
        Product addProd = new Product(name,price);
        addProd.setQuantityOnStock(quantityOnStock);
        addProd.setRating(rating);
        productService.addProduct(addProd);
    }

    private static int checkMenu(int menu, int expectedMax) {
        while (true) {
            if (menu < 0 && menu > expectedMax) {
                System.out.println("Wrong menu number, try again");
                menu = scan.nextInt();
            } else
                return menu;
        }
    }
}