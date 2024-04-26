package com.bintics.orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private String id;

    private List<OrderProductRequest> products;

}
