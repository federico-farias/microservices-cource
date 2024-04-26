package com.bintics.orders.repository;

import com.bintics.orders.model.OrderProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProductModel, String> {

    List<OrderProductModel> findByOrderId(String orderId);

}
