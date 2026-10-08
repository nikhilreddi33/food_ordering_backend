package com.foodordering.foodordering.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodordering.foodordering.entity.Order;
import com.foodordering.foodordering.repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // =========================
    // CREATE ORDER
    // =========================

    @PostMapping
    public Order createOrder(@RequestBody Order order) {

        order.setOrderStatus("PLACED");

        return orderRepository.save(order);
    }

    // =========================
    // GET ALL ORDERS
    // =========================

    @GetMapping
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    // =========================
    // GET ORDER BY ID
    // =========================

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // =========================
    // CUSTOMER ORDERS
    // =========================

    @GetMapping("/customer/{customerId}")
    public List<Order> getCustomerOrders(
            @PathVariable Long customerId) {

        return orderRepository.findByCustomerId(customerId);
    }

    // =========================
    // RESTAURANT ORDERS
    // =========================

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getRestaurantOrders(
            @PathVariable Long restaurantId) {

        return orderRepository.findByRestaurantId(restaurantId);
    }

    // =========================
    // DRIVER ORDERS
    // =========================

    @GetMapping("/driver/{driverId}")
    public List<Order> getDriverOrders(
            @PathVariable Long driverId) {

        return orderRepository.findByDriverId(driverId);
    }

    // =========================
    // RESTAURANT ACCEPTS
    // =========================

    @PutMapping("/{id}/accept")
    public Order acceptOrder(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("RESTAURANT_ACCEPTED");

        return orderRepository.save(order);
    }

    // =========================
    // RESTAURANT REJECTS
    // =========================

    @PutMapping("/{id}/reject")
    public Order rejectOrder(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("RESTAURANT_REJECTED");

        return orderRepository.save(order);
    }

    // =========================
    // RESTAURANT STARTS PREPARING
    // =========================

    @PutMapping("/{id}/preparing")
    public Order prepareOrder(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("PREPARING");

        return orderRepository.save(order);
    }

    // =========================
    // READY FOR PICKUP
    // =========================

    @PutMapping("/{id}/ready")
    public Order readyForPickup(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("READY_FOR_PICKUP");

        return orderRepository.save(order);
    }

    // =========================
    // DRIVER ACCEPTS DELIVERY
    // =========================

    @PutMapping("/{id}/driver-accept")
    public Order driverAcceptOrder(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("DRIVER_ACCEPTED");

        return orderRepository.save(order);
    }

    // =========================
    // DRIVER PICKS UP ORDER
    // =========================

    @PutMapping("/{id}/picked-up")
    public Order pickedUpOrder(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("PICKED_UP");

        return orderRepository.save(order);
    }

    // =========================
    // OUT FOR DELIVERY
    // =========================

    @PutMapping("/{id}/out-for-delivery")
    public Order outForDelivery(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("OUT_FOR_DELIVERY");

        return orderRepository.save(order);
    }

    // =========================
    // ORDER DELIVERED
    // =========================

    @PutMapping("/{id}/delivered")
    public Order deliveredOrder(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus("DELIVERED");

        return orderRepository.save(order);
    }
}