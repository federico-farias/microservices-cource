package com.bintics.orders.service;

import com.bintics.orders.dto.AddProductsToOrder;
import com.bintics.orders.dto.OrderProductResponse;
import com.bintics.orders.dto.OrderRequest;
import com.bintics.orders.dto.OrderResponse;

import java.util.List;

public interface OrdersService {
    String create(OrderRequest request);

    List<OrderResponse> getAllOrders();

    List<OrderProductResponse> getProductsByOrder(String orderId);

    void delete(String id);

    void addProductsToOrder(AddProductsToOrder request);
}
