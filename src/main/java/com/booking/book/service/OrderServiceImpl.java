package com.booking.book.service;

import com.booking.book.dao.OrderRepository;
import com.booking.book.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        if(byId.isEmpty()){
            throw new RuntimeException("Room not found");
        }else{
            return byId.get();
        }
    }

    @Override
    public void saveOrder(Order order) {
        //Date startDate = Date.from(order.getStartDateTime().atStartOfDay(ZoneId.systemDefault()).toInstant());
        //Date endDate = Date.from( .atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Order> orders = orderRepository.canBeAdded(order.getRoomId() ,order.getStartDateTime(),order.getEndDateTime());
        System.out.println(order.getStartDateTime());

        if(orders.size() == 0){

            Order save = orderRepository.save(order);
            System.out.println(save + "saved");
        }
        else{

            throw new RuntimeException("Cant be added");
        }
    }

    @Override
    public void deleteById(Long theId) {
        orderRepository.deleteById(theId);
    }
}
