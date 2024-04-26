package com.bintics.orders.receiver;

import com.bintics.orders.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class ProductCreatedReceiver {

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.queueName)
    public void onMessage(ProductCreatedEvent message) {
        System.out.println(message);
    }

}
