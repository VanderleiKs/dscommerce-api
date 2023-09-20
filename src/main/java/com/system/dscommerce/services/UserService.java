package com.system.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dscommerce.dtos.UserDTO;
import com.system.dscommerce.entities.Role;
import com.system.dscommerce.entities.User;
import com.system.dscommerce.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var result = repository.searchUserAndRolesByEmail(username);

       if(result.size() == 0) throw new UsernameNotFoundException("Email not found!");

       var user = new User();
       user.setEmail(result.get(0).getUsername());
       user.setPassword(result.get(0).getPassWord());
       result.stream().forEach(p -> user.addRole(new Role(p.getRoleId(), p.getAuthority())));

       return user;
    }

    protected User authenticated(){
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwt = (Jwt) auth.getPrincipal();
            String username = jwt.getClaim("username");
            return repository.findByEmail(username).get();
        }
        catch(Exception e){
            throw new UsernameNotFoundException("Email not found!");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO getMe(){
        return new UserDTO(authenticated());
    }
}
