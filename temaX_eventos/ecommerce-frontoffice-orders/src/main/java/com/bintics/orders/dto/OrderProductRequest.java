package com.bintics.orders.dto;

import lombok.Data;

@Data
public class OrderProductRequest {

    private String productId;

    private Integer quantity;

}
