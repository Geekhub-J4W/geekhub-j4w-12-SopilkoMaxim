package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "buyer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Component
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;

}
