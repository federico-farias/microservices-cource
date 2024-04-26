package com.bintics.products.service;

import com.bintics.products.dto.ProductRequest;
import com.bintics.products.dto.ProductResponse;

import java.util.List;

public interface ProductsService {
    String create(ProductRequest request);

    List<ProductResponse> getAll();

    ProductResponse findById(String id);

    void update(ProductRequest request);

    void delete(String id);

}
