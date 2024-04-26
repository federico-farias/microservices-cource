package com.bintics.products.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String id;

    private String name;

    private Double price;

    private Date createdAt;

    private Date updatedAt;
}
