package com.booking.book.service;

import com.booking.book.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    void saveOrder(Order order);

    void deleteById(Long theId);

    void removeOrder(Long id);


}
