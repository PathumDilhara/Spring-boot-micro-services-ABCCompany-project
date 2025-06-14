package com.example.userorder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserOrderDTO {
    private int id;
    private int itemId;
    private Date orderDate;
    private int amount;
}
