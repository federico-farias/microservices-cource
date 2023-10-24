package com.bintics.products.service;

import co.elastic.apm.api.CaptureSpan;
import com.bintics.products.dto.ProductResponse;
import com.bintics.products.dto.ProductRequest;
import com.bintics.products.model.ProductModel;
import com.bintics.products.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository repository;

    @Override
    @CaptureSpan(type = "service", action = "create")
    public String create(ProductRequest request) {
        var id = UUID.randomUUID().toString();
        var now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        ProductModel model = new ProductModel(id, request.getName(), request.getPrice(), date, date);
        this.repository.save(model);
        return id;
    }

    @Override
    @CaptureSpan(type = "service", action = "getAll")
    public List<ProductResponse> getAll() {
        var listResponse = this.repository.findAll().stream()
                .map(m -> new ProductResponse(
                        m.getId(),
                        m.getName(),
                        m.getPrice(),
                        m.getCreatedAt(),
                        m.getUpdatedAt()))
                .collect(Collectors.toList());
        return listResponse;
    }

    @Override
    @CaptureSpan(type = "service", action = "update")
    public void update(ProductRequest request) {
        var model = this.repository.findById(request.getId()).orElseThrow();
        var now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        model.setName(request.getName());
        model.setPrice(request.getPrice());
        model.setUpdatedAt(date);
        this.repository.save(model);
    }

    @Override
    @CaptureSpan(type = "service", action = "delete")
    public void delete(String id) {
        var todo = this.repository.findById(id).orElseThrow();
        this.repository.delete(todo);
    }

}
