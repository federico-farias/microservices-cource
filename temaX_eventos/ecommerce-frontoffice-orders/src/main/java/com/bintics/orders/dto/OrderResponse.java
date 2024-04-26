package com.bintics.orders.dto;

import com.bintics.orders.model.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderResponse {

    private String id;
    private OrderStatusEnum status;
    private Double total;
    private Date createdAt;
    private Date updatedAt;

}
