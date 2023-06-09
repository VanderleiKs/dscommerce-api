package com.system.dscommerce.dtos;

import com.system.dscommerce.entities.Product;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        String imgUrl) {

    public ProductDTO(Product p) {
        this(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getImgUrl());
    }
}
