package com.bintics.orders.service;

import com.bintics.orders.dto.*;
import com.bintics.orders.gateway.ProductsGateway;
import com.bintics.orders.model.OrderModel;
import com.bintics.orders.model.OrderProductModel;
import com.bintics.orders.model.OrderStatusEnum;
import com.bintics.orders.repository.OrderProductRepository;
import com.bintics.orders.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    private final OrderProductRepository orderProductRepository;

    private final ProductsGateway productsGateway;

    @Override
    @Transactional
    public String create(OrderRequest request) {
        var orderId = UUID.randomUUID().toString();
        var nowOrderDate = LocalDateTime.now();
        var instant = nowOrderDate.atZone(ZoneId.systemDefault()).toInstant();
        var orderDate = Date.from(instant);

        OrderModel order = new OrderModel();
        order.setId(orderId);
        order.setStatus(OrderStatusEnum.OPEN.getValue());
        order.setCreatedAt(orderDate);
        order.setUpdatedAt(orderDate);
        this.orderRepository.save(order);

        var orderProducts = new LinkedList<OrderProductModel>();
        for (OrderProductRequest productRequest : request.getProducts()) {
            var orderProductId = UUID.randomUUID().toString();
            try {
                var product = this.productsGateway.findById(productRequest.getProductId());
                if (product == null) {
                    continue;
                }
                var subTotal = productRequest.getQuantity() * product.getPrice();
                var nowProductDate = LocalDateTime.now();
                var productDate = Date.from(nowProductDate.atZone(ZoneId.systemDefault()).toInstant());
                orderProducts.add(new OrderProductModel(
                        orderProductId,
                        product.getId(),
                        orderId,
                        productRequest.getQuantity(),
                        subTotal,
                        productDate,
                        productDate));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        this.orderProductRepository.saveAll(orderProducts);
        return orderId;
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        var listResponse = this.orderRepository.findAll().stream()
                .map(m -> new OrderResponse(
                        m.getId(),
                        OrderStatusEnum.getEnumFrom(m.getStatus()),
                        m.getTotal(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()))
                .collect(Collectors.toList());
        return listResponse;
    }

    @Override
    public List<OrderProductResponse> getProductsByOrder(String orderId) {
        var orderProducts = this.orderProductRepository.findByOrderId(orderId)
                .stream().map(opm -> new OrderProductResponse(
                        opm.getProductId(),
                        opm.getQuantity(),
                        opm.getCreatedAt(),
                        opm.getUpdatedAt()
                )).collect(Collectors.toList());
        return orderProducts;
    }

    @Override
    public void addProductsToOrder(AddProductsToOrder request) {
        var order = this.orderRepository.findById(request.getOrderId()).orElseThrow();
        for (var productRequest : request.getProducts()) {
            var product = this.productsGateway.findById(productRequest.getProductId());
            var subTotal = productRequest.getQuantity() * product.getPrice();

        }
    }

    @Override
    public void delete(String id) {
        var todo = this.orderRepository.findById(id).orElseThrow();
        this.orderRepository.delete(todo);
    }

}
