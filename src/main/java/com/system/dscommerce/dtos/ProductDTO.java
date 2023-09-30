package com.system.dscommerce.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.system.dscommerce.entities.Product;

public record ProductDTO(
        Long id,
        String name,
        String description,
        Double price,
        String imgUrl,
        List<CategoryDTO> categories
        ) {

    public ProductDTO(Product p) {
        this(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getImgUrl(),
                p.getCategories().stream().map(cat -> new CategoryDTO(cat)).collect(Collectors.toList())
                );
    }
}
