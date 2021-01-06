package com.booking.book.service;

import com.booking.book.entity.Order;
import com.booking.book.entity.Room;

import java.util.List;

public interface OrderService {

    public List<Order> getAllOrders();

    public Order getOrderById(Long id);

    public void saveOrder(Order order);

    public void deleteById(Long theId);


}
