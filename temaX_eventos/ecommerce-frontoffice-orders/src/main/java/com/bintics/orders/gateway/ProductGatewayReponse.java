package com.bintics.orders.gateway;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductGatewayReponse {

    private String id;
    private String name;
    private Double price;
    private Date createdAt;
    private Date updatedAt;

}
