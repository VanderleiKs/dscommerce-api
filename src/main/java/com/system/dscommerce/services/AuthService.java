package com.system.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dscommerce.services.exceptions.ForbiddenExceptionService;

@Service
public class AuthService {
    
    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId){
        var user = userService.authenticated();
        if(!user.hasRole("ROLE_ADMIN") && !user.getId().equals(userId)){
            throw new ForbiddenExceptionService("Acesso negado");
        }
    }
}
