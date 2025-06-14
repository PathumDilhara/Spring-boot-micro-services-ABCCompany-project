package com.example.userorder.service;

import com.example.userorder.dto.UserOrderDTO;
import com.example.userorder.entity.UserOrder;
import com.example.userorder.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserOrderDTO> getAllOrders(){
        List<UserOrder> orders = orderRepo.findAll();
        return modelMapper.map(orders, new TypeToken<List<UserOrderDTO>>(){}.getType());
    }

    public UserOrderDTO saveOrderDTO(UserOrderDTO userOrderDTO){
        UserOrder order =  modelMapper.map(userOrderDTO, UserOrder.class);
        orderRepo.save(order);
        return userOrderDTO;
    }

    public UserOrderDTO updateOrder(UserOrderDTO userOrderDTO){
        orderRepo.save(modelMapper.map(userOrderDTO, UserOrder.class));
        return userOrderDTO;
    }

    public boolean deleteOrder(UserOrderDTO userOrderDTO){
        orderRepo.delete(modelMapper.map(userOrderDTO, UserOrder.class));
        return true;
    }
}
