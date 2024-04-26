package com.bintics.products.service;

import com.bintics.products.config.RabbitMQConfig;
import com.bintics.products.dto.ProductRequest;
import com.bintics.products.dto.ProductResponse;
import com.bintics.products.model.ProductCreatedEvent;
import com.bintics.products.model.ProductDocument;
import com.bintics.products.model.ProductModel;
import com.bintics.products.repository.ProductDocumentRepository;
import com.bintics.products.repository.ProductEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    private final RabbitTemplate rabbitTemplate;

    private final ProductEntityRepository productEntityRepository;

    private final ProductDocumentRepository productDocumentRepository;

    @Override
    public String create(ProductRequest request) {
        var id = UUID.randomUUID().toString();
        var now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        ProductModel model = new ProductModel(id, request.getName(), request.getPrice(), date, date);
        this.productEntityRepository.save(model);
        this.productDocumentRepository.save(new ProductDocument(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        ));
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, RabbitMQConfig.queueKey, new ProductCreatedEvent(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getCreatedAt(),
                model.getUpdatedAt()
        ));
        return id;
    }

    @Override
    public List<ProductResponse> getAll() {
        var listResponse = this.productEntityRepository.findAll().stream()
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
    public ProductResponse findById(String id) {
        return this.productEntityRepository.findById(id).map(prod -> new ProductResponse(
                prod.getId(),
                prod.getName(),
                prod.getPrice(),
                prod.getCreatedAt(),
                prod.getUpdatedAt()
        )).orElse(null);
    }

    @Override
    public void update(ProductRequest request) {
        var model = this.productEntityRepository.findById(request.getId()).orElseThrow();
        var now = LocalDateTime.now();
        Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        model.setName(request.getName());
        model.setPrice(request.getPrice());
        model.setUpdatedAt(date);
        this.productEntityRepository.save(model);
    }

    @Override
    public void delete(String id) {
        var todo = this.productEntityRepository.findById(id).orElseThrow();
        this.productEntityRepository.delete(todo);
    }

}
