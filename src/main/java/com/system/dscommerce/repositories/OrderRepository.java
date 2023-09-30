package com.system.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
