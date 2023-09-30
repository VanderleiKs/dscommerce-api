package com.system.dscommerce.dtos;

import com.system.dscommerce.entities.User;

public record ClientDTO(
    Long id,
    String name
) {

    public ClientDTO(User e){
        this(e.getId(), e.getName());
    }
    
}
