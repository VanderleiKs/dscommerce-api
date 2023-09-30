package com.system.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.dscommerce.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
