package com.bintics.products.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {

    private String id;

    private String name;

    private Double price;

    private Date createdAt;

    private Date updatedAt;

}
