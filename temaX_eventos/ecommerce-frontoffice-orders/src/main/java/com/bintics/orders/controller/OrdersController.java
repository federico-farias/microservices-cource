package com.bintics.orders.controller;

import com.bintics.orders.dto.AddProductsToOrder;
import com.bintics.orders.dto.OrderRequest;
import com.bintics.orders.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService service;

    @PostMapping
    public ResponseEntity create(@RequestBody OrderRequest request) {
        var id = this.service.create(request);
        return ResponseEntity.created(URI.create(String.format("/orders/%s", id))).build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        var list = this.service.getAllOrders();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{orderId}/products")
    public ResponseEntity getProductsByOrder(@PathVariable("orderId") String orderId) {
        var products = this.service.getProductsByOrder(orderId);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{orderId}/products")
    public ResponseEntity addProduct(@PathVariable("orderId") String orderId, @RequestBody AddProductsToOrder request) {
        request.setOrderId(orderId);
        this.service.addProductsToOrder(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
