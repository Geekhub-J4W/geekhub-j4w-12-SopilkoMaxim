package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoApplication {

    private static DemoInputData demo;

    public DemoApplication(DemoInputData demo) {
        DemoApplication.demo = demo;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        //Start once to fill the DB
        //demo.save();

        //Start to delete all fields from DB
        //demo.deleteAllData();

        System.out.println(demo.getBuyerSpendingAndOrdersByQuarter(1L, LocalDateTime.now().minusMonths(2).toLocalDate(),LocalDateTime.now().toLocalDate()));
        System.out.println(demo.findOrdersByMinMaxPrice());

    }

}
