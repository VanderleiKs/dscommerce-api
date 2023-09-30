package com.system.dscommerce.dtos;

import com.system.dscommerce.entities.Category;

public record CategoryDTO(
    Long id,
    String name
) {

    public CategoryDTO(Category cat){
        this(
            cat.getId(), 
            cat.getName()
            );
    }
    
}
