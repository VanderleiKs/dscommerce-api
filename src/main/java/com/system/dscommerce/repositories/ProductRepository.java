package com.system.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
