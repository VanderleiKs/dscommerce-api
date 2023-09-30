package com.system.dscommerce.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dscommerce.dtos.OrderDTO;
import com.system.dscommerce.entities.Order;
import com.system.dscommerce.entities.OrderItem;
import com.system.dscommerce.entities.OrderStatus;
import com.system.dscommerce.repositories.OrderItemRepository;
import com.system.dscommerce.repositories.OrderRepository;
import com.system.dscommerce.repositories.ProductRepository;
import com.system.dscommerce.services.exceptions.NotFoundExceptionService;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private OrderItemRepository orderItemrepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        var order = repository.findById(id).orElseThrow(
                () -> new NotFoundExceptionService("Produto não encontrado"));
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        var order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());
        
        for (var i : dto.getItems()) {
            var product = productRepository.getReferenceById(i.getProductId());
            var item = new OrderItem(order, product, i.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemrepository.saveAll(order.getItems());
        return new OrderDTO(order);
    }

}
