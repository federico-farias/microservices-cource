package com.bintics.orders.gateway;

import com.bintics.orders.model.ProductModel;
import com.bintics.orders.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class ProductsGatewayImpl implements ProductsGateway {

    private final ProductsFeignClient client;

    private final ProductRepository productRepository;

    @Override
    public List<ProductGatewayReponse> getAllProducts() {
        return this.client.getAllProducts();
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "findById")
    public ProductGatewayReponse findById(String productsId) {
        Optional<ProductModel> productModel = this.productRepository.findById(productsId);
        ProductModel pm = productModel.orElseGet(() -> {
            var productRespose = this.client.findById(productsId);
            return this.productRepository.save(new ProductModel(productRespose.getId(), productRespose.getPrice()));
        });
        var resp = new ProductGatewayReponse();
        resp.setId(pm.getId());
        resp.setPrice(pm.getPrice());
        return resp;
    }

    public ProductGatewayReponse findById(String productsId, Exception ex) {
        log.info("fallback: " + productsId + ", exeption: " + ex.getMessage());
        var opProduct = this.productRepository.findById(productsId);
        if (opProduct.isEmpty()) {
            return null;
        }
        var prod = opProduct.get();
        var resp = new ProductGatewayReponse();
        resp.setId(prod.getId());
        resp.setPrice(prod.getPrice());
        return resp;
    }

}
