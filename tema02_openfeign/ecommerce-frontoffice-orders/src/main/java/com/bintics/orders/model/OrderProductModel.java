package com.bintics.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductModel implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String productId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double subTotal;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
