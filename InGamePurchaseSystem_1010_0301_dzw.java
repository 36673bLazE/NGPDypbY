// 代码生成时间: 2025-10-10 03:01:30
package com.example.ingamepurchasesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/purchase")
public class InGamePurchaseSystem {

    private static final List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    @PostMapping("/order")
    public PurchaseOrder createOrder(@RequestBody PurchaseOrder order) {
        purchaseOrders.add(order);
        return order;
    }

    @GetMapping("/orders")
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrders;
    }

    @GetMapping("/order/{id}")
    public PurchaseOrder getOrderById(@PathVariable Long id) {
        return purchaseOrders.stream()
            .filter(purchaseOrder -> purchaseOrder.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new PurchaseOrderNotFoundException("PurchaseOrder not found with id: " + id));
    }

    @ExceptionHandler(PurchaseOrderNotFoundException.class)
    public ResponseEntity<String> handlePurchaseOrderNotFoundException(PurchaseOrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(InGamePurchaseSystem.class, args);
    }
}

class PurchaseOrder {
    private Long id;
    private String gameId;
    private String playerId;
    private double amount;

    // Constructor, getters and setters
    public PurchaseOrder() {}

    public PurchaseOrder(Long id, String gameId, String playerId, double amount) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

class PurchaseOrderNotFoundException extends RuntimeException {
    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }
}