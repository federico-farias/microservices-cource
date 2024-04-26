package com.bintics.products.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("product_created_events")
@AllArgsConstructor
@NoArgsConstructor
public class ProductDocument {

    private String id;

    private String name;

    private Double price;

    private Date createdAt;

    private Date updatedAt;

}
