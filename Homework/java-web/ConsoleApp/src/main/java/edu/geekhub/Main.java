package edu.geekhub;

import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        ProductService productRepository = new ProductService();
        System.out.println("Menu:\n1.Add product\n2.Delete product\n3.Show all products\n4.Sort and show by price");
        System.out.println("5.Show and sort by name\n6.Exit");
        int menu = 0;
        String name;
        int price;
        int id;
        while (menu != 6) {
            menu = scan.nextInt();
            menu = checkMenu(menu);
            switch (menu) {
                case 1 -> {
                    System.out.println("Enter name of product:");
                    scan.nextLine();
                    name = scan.nextLine();
                    System.out.println("Enter price");
                    price = scan.nextInt();
                    productRepository.addProduct(new Product(name, price));
                }
                case 2 -> {
                    System.out.println("Enter product Id which you want to delete");
                    id = scan.nextInt();
                    productRepository.deleteProduct(id);
                }
                case 3 -> {
                    System.out.println(productRepository.getProducts());
                }
                case 4 -> {
                    System.out.println(productRepository.sortedListByPrice());
                }
                case 5 -> {
                    System.out.println(productRepository.sortedListByName());;
                }
            }
        }

    }

    private static int checkMenu(int menu) {
        while (true) {
            if (menu < 0 && menu > 6) {
                System.out.println("Wrong menu number, try again");
                menu= scan.nextInt();
            }
            else
              return menu;
        }
    }
}