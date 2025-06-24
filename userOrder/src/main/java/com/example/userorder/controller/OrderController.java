package com.example.userorder.controller;

import com.example.base.dto.OrderEventDTO;
import com.example.userorder.common.OrderResponse;
import com.example.userorder.dto.UserOrderDTO;
import com.example.userorder.kafka.OrderProducer;
import com.example.userorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProducer orderProducer;

    @GetMapping("/getAll")
    public List<UserOrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/addOrder")
    public OrderResponse saveOrder(@RequestBody UserOrderDTO orderDTO){
        System.out.println("############### saving order");

        OrderEventDTO orderEventDTO = new OrderEventDTO();
        orderEventDTO.setMessage("Order is Commited 1");
        orderEventDTO.setStatus("pending");
        orderProducer.sendMessage(orderEventDTO);

        return orderService.saveOrderDTO(orderDTO);
    }

    @PutMapping("/updateOrder")
    public UserOrderDTO updateOrder(@RequestBody UserOrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/deleteOrder")
    public boolean deleteOrder(@RequestBody UserOrderDTO orderDTO){
        return orderService.deleteOrder(orderDTO);
    }
}
