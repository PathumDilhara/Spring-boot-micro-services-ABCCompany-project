package com.example.userorder.controller;

import com.example.userorder.dto.UserOrderDTO;
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

    @GetMapping("/getAllOrders")
    public List<UserOrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/saveOrder")
    public UserOrderDTO saveOrder(@RequestBody UserOrderDTO orderDTO){
        return orderService.saveOrderDTO(orderDTO);
    }

    @PutMapping("/updateOrder")
    public UserOrderDTO updateOrder(@RequestBody UserOrderDTO orderDTO){
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping("/deleteOrders")
    public boolean deleteOrder(@RequestBody UserOrderDTO orderDTO){
        return orderService.deleteOrder(orderDTO);
    }
}
