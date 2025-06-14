package com.example.userorder.repo;

import com.example.userorder.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<UserOrder,Integer> {
}
