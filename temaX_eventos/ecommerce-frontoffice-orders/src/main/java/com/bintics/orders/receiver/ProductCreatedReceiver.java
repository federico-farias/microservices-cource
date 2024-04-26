package com.bintics.orders.receiver;

import com.bintics.orders.config.RabbitMQConfig;
import com.bintics.orders.model.ProductModel;
import com.bintics.orders.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ProductCreatedReceiver {

    private final ProductRepository productRepository;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.queueName)
    public void onMessage(ProductCreatedEvent message) {
        ProductModel productModel = new ProductModel();
        productModel.setId(message.getId());
        productModel.setPrice(message.getPrice());
        this.productRepository.save(productModel);
    }

}
