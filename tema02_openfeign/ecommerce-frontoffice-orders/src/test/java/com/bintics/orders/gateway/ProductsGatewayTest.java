package com.bintics.orders.gateway;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringBootTest
class ProductsGatewayTest {

    @Autowired
    private ProductsGateway repository;

    @Test
    void getAllProducts() {
        assertNotNull(repository);
        var prods = this.repository.getAllProducts();
        assertNotNull(prods);
    }

    @Test
    void findById() {
        assertNotNull(repository);
        var prods = this.repository.findById("0bd532ed-965e-4ebb-8ab3-c923f504f508-");
        assertNotNull(prods);
    }


}