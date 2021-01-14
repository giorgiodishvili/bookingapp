package com.booking.book.service;

import com.booking.book.ecomm.ConnectIT;
import com.booking.book.dao.OrderRepository;
import com.booking.book.entity.Order;
import com.booking.book.exception.OrderCanNotBeAddedException;
import com.booking.book.exception.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
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
            orderRepository.save(order);
        } else {
            throw new OrderCanNotBeAddedException();
        }
    }

    public void deleteById(Long theId) {
        orderRepository.deleteById(theId);
    }

    public List<Order> getOrderByTranId(String id){
        return orderRepository.findByTransactionId(id);
    }

    public void removeOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
