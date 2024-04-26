package com.bintics.orders.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel implements Serializable {

    @Id
    private String id;

    @Column
    private Double price;

}
