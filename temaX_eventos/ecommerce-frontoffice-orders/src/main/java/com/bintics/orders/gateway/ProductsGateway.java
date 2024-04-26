package com.bintics.orders.gateway;

import java.util.List;

public interface ProductsGateway {

    List<ProductGatewayReponse> getAllProducts();

    ProductGatewayReponse findById(String productsId);

}
