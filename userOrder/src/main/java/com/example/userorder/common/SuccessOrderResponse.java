package com.example.userorder.common;

import com.example.userorder.dto.UserOrderDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;

@Getter
public class SuccessOrderResponse implements OrderResponse {

    @JsonUnwrapped
    private final UserOrderDTO userOrderDTO;

    public SuccessOrderResponse(UserOrderDTO userOrderDTO) {
        this.userOrderDTO = userOrderDTO;
    }
}
