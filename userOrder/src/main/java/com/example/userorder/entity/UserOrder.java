package com.example.userorder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserOrder {
    @Id
    private int id;
    private int itemId;
    private Date orderDate;
    private int amount;
}
