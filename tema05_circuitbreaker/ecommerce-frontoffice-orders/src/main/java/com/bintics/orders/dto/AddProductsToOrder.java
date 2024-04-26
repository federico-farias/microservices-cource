package com.bintics.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductsToOrder {

    private String orderId;

    private List<OrderProductRequest> products;

}
