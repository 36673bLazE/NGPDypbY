// 代码生成时间: 2025-10-01 19:50:45
package com.b2b.purchasesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class B2BPurchaseSystem {

    private static List<PurchaseOrder> purchaseOrders = new ArrayList<>();
    private static int orderId = 1;

    @GetMapping("/orders")
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrders;
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable int id) {
        PurchaseOrder order = purchaseOrders.stream()
            .filter(p -> p.getId() == id)
            .findFirst()
            .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        return ResponseEntity.ok(order);
    }

    @PostMapping("/orders")
    public PurchaseOrder createOrder(@RequestBody PurchaseOrder order) {
        order.setId(orderId++);
        purchaseOrders.add(order);
        return order;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrderNotFoundException(OrderNotFoundException ex) {
        return ex.getMessage();
    }

    public static void main(String[] args) {
        SpringApplication.run(B2BPurchaseSystem.class, args);
    }

    public static class PurchaseOrder {
        private int id;
        private String item;
        private double price;
        private int quantity;

        // Constructors, getters and setters
        public PurchaseOrder() {
        }

        public PurchaseOrder(int id, String item, double price, int quantity) {
            this.id = id;
            this.item = item;
            this.price = price;
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }
}
