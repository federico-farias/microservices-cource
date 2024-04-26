package com.bintics.orders.gateway;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class ProductsGatewayImpl implements ProductsGateway {

    private final ProductsFeignClient client;

    private final ValueOperations<String, Object> opsValue;

    public ProductsGatewayImpl(ProductsFeignClient client, RedisTemplate<String, Object> redisTemplate) {
        this.client = client;
        this.opsValue = redisTemplate.opsForValue();
    }

    @Override
    public List<ProductGatewayReponse> getAllProducts() {
        return this.client.getAllProducts();
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "findById")
    public ProductGatewayReponse findById(String productsId) {
        var product = (ProductGatewayReponse) this.opsValue.get(productsId);
        if (product != null) {
            return product;
        }
        product = client.findById(productsId);
        this.opsValue.set(productsId, product, 1L, TimeUnit.MINUTES);
        return product;
    }

    public ProductGatewayReponse findById(String productsId, Exception ex) {
        log.info("fallback: " + productsId + ", exeption: " + ex.getMessage());
        var product = (ProductGatewayReponse) this.opsValue.get(productsId);
        return product;
    }

}
