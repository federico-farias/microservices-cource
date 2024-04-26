package com.bintics.orders.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "products-service", url = "${app.gateway.products}")
public interface ProductsFeignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<ProductGatewayReponse> getAllProducts();

    @RequestMapping(method = RequestMethod.GET, value = "/{productsId}", consumes = "application/json", produces = "application/json")
    ProductGatewayReponse findById(@PathVariable("productsId") String productsId);

}
