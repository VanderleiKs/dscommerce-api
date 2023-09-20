package com.system.dscommerce.dtos;

import com.system.dscommerce.entities.Product;

public record ProductMinDTO(
        Long id,
        String name,
        Double price,
        String imgUrl) {

    public ProductMinDTO(Product p) {
        this(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getImgUrl());
    }
}
