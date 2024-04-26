package com.bintics.orders.gateway;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "products-service")
public interface ProductsGateway {

    @RequestMapping(method = RequestMethod.GET)
    List<ProductGatewayReponse> getAllProducts();

    // Enviar la lista de Id's de producto podría ser más óptimo en lugar de mandar id por id
    // En un entorno real podría ocurrir que el API no este preparada para proveer información de manera más óptima
    @RequestMapping(method = RequestMethod.GET, value = "/{productsId}", consumes = "application/json", produces = "application/json")
    ProductGatewayReponse findById(@PathVariable("productsId") String productsId);


}
