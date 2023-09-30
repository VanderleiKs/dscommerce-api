package com.system.dscommerce.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.system.dscommerce.entities.Order;
import com.system.dscommerce.entities.OrderStatus;

public class OrderDTO {
    private Long id;
    private Instant moment;
    private OrderStatus status;
    private ClientDTO client;
    private PaymentDTO payment;
    private List<OrderItemsDTO> items = new ArrayList<>();
    public OrderDTO() {
    }
    public OrderDTO(Long id, Instant moment, OrderStatus status, ClientDTO client, PaymentDTO payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }
    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.status = entity.getStatus();
        this.client = new ClientDTO(entity.getClient());
        this.payment = entity.getPayment() == null ? null : new PaymentDTO(entity.getPayment());
        entity.getItems().forEach(i -> items.add(new OrderItemsDTO(i)));
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDTO getClient() {
        return client;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public List<OrderItemsDTO> getItems() {
        return items;
    }

    public Double getTotal(){
        var total = 0.0;
        for (var i : items) total += i.getSubtotal();
        return total;
    }
    
}
