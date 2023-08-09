package com.example.demo.controller;

import com.example.demo.dto.BuyerSpendingDTO;
import com.example.demo.entity.Buyer;
import com.example.demo.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {
    private final BuyerService buyerService;

    @Autowired
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    public ResponseEntity<Void> addBuyer(@RequestBody Buyer buyer) {
        buyerService.add(buyer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Buyer>> getAllBuyers() {
        List<Buyer> buyers = buyerService.getAll();
        return ResponseEntity.ok(buyers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buyer> getBuyerById(@PathVariable Long id) {
        Buyer buyer = buyerService.getById(id);
        if (buyer != null) {
            return ResponseEntity.ok(buyer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuyer(@PathVariable Long id) {
        Buyer buyer = buyerService.getById(id);
        if (buyer != null) {
            buyerService.remove(buyer);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBuyer(@PathVariable Long id, @RequestBody Buyer updatedBuyer) {
        Buyer existingBuyer = buyerService.getById(id);
        if (existingBuyer != null) {
            updatedBuyer.setId(id);
            buyerService.update(updatedBuyer);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/spending")
    public ResponseEntity<BuyerSpendingDTO> getBuyerSpendingAndOrdersByQuarter(
            @RequestParam long buyerId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return ResponseEntity.ok(buyerService.getBuyerSpendingAndOrdersByQuarter(buyerId,startDate,endDate));
    }
}
