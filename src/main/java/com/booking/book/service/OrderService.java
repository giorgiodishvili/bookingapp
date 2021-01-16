package com.booking.book.service;

import com.booking.book.dao.OrderRepository;
import com.booking.book.entity.Orders;
import com.booking.book.exception.OrderCanNotBeAddedException;
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

    public List<Orders> getAllOrders() {
        return orderRepository.all();
    }

    public Orders getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Orders saveOrder(Orders order) {

        List<Orders> orders = orderRepository.isTimeAvailableByRoomId(order.getRoomId(), order.getStartDateTime(), order.getEndDateTime());

        int difference = order.getStartDateTime().compareTo(LocalDate.now());

        boolean moreThanCurrentDate = difference >= 0;

        if (orders.size() == 0 && moreThanCurrentDate) {
            return orderRepository.save(order);
        } else {
            throw new OrderCanNotBeAddedException();
        }
    }

    public List<Orders> getOrderByTranId(String id) {
        return orderRepository.findByTransactionId(id);
    }
}
