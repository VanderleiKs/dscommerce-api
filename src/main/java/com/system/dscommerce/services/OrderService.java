package com.system.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dscommerce.dtos.OrderDTO;
import com.system.dscommerce.repositories.OrderRepository;
import com.system.dscommerce.services.exceptions.NotFoundExceptionService;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        var order = repository.findById(id).orElseThrow(
                () -> new NotFoundExceptionService("Produto não encontrado"));
        return new OrderDTO(order);
    }

}
