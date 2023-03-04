package com.hasitha.orderservice.repository;

import com.hasitha.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order , Long> {
}
