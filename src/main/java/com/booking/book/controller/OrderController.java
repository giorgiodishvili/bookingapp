package com.booking.book.controller;

import com.booking.book.entity.Orders;
import com.booking.book.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //for testing purposes (@JSONIgnore)
    @GetMapping("/list")
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/save")
    public Orders saveOrder(@RequestBody Orders orders) {
        return orderService.saveOrder(orders);
    }

}
