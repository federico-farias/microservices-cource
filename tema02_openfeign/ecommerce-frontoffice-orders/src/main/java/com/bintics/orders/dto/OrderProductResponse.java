package com.bintics.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductResponse {

    private String productId;

    private Integer quantity;

    private Date createdAt;

    private Date updatedAt;

}
