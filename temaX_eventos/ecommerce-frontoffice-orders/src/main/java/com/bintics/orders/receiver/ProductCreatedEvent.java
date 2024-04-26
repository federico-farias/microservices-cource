package com.bintics.orders.receiver;

import lombok.Data;

import java.util.Date;

@Data
public class ProductCreatedEvent {

    private String id;

    private String name;

    private Double price;

    private Date createdAt;

    private Date updatedAt;

}
