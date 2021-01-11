package com.booking.book.service;

import com.booking.book.dao.OrderRepository;
import com.booking.book.entity.Order;
import com.booking.book.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public void saveOrder(Order order) {

        List<Order> orders = orderRepository.isTimeAvailableByRoomId(order.getRoomId(), order.getStartDateTime(), order.getEndDateTime());

        int difference = order.getStartDateTime().compareTo(LocalDate.now());
        boolean moreThanCurrentDate = difference >= 0;
        if (orders.size() == 0 && moreThanCurrentDate) {

            Order save = orderRepository.save(order);
        } else {

            throw new RuntimeException("Cant be added");
        }
    }

    public void deleteById(Long theId) {
        orderRepository.deleteById(theId);
    }

    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
