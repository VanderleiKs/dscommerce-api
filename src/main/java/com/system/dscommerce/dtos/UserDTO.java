package com.system.dscommerce.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.system.dscommerce.entities.User;

public record UserDTO(
    Long id,
    String name,
    String email,
    String phone,
    LocalDate birthDate,
    List<String> roles
){

    public UserDTO(User entity){
        this(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getBirthDate(),
            entity.getRoles().stream().map(role -> role.getAuthority()).collect(Collectors.toList())
            );
    }
}
