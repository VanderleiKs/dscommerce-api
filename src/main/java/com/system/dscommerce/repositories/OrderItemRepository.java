package com.system.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.dscommerce.entities.OrderItem;
import com.system.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
