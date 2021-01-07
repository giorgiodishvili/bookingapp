package com.booking.book.controller;

import com.booking.book.entity.Order;
import com.booking.book.entity.Room;
import com.booking.book.service.OrderService;
import com.booking.book.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/addOrder")
    public String showFormForOrder(@RequestParam("roomId")Long id,Model model) {

        // create model attribute to bind form data
        Order theOrder = new Order();
        theOrder.setRoomId(roomService.getRoomById(id));
//        model.addAttribute("startDate", LocalDateTime.now());
//        model.addAttribute("endDate", LocalDateTime.now());

        model.addAttribute("order", theOrder);

        return "orders/order-form";
    }

    @PostMapping("/save")
    public String saveOrder(@ModelAttribute("order")Order order) {
        System.out.println(order + "in orderController");
            orderService.saveOrder(order);
            System.out.println(order + "passed try catch");
            return "redirect:/order/list" ;
    }

    @GetMapping("/single/{id}")
    public String getSingleOrder(@PathVariable("id")Long id,Model theModel){
        Order orderById = orderService.getOrderById(id);
theModel.addAttribute("order", orderById);
        // redirect to /employees/list
        return "orders/single-order";
    }

    @GetMapping("/list")
    public String getAllOrders(Model theModel){
        List<Order> theOrders = orderService.getAllOrders();

        // add to the spring model
        theModel.addAttribute("orders", theOrders);
        return "orders/order-list";

    }



}
