package com.system.dscommerce.dtos;

import com.system.dscommerce.entities.OrderItem;

public class OrderItemsDTO {

    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;

    public OrderItemsDTO() {
    }

    public OrderItemsDTO(Long productId, String name, Double price, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }
    
    public OrderItemsDTO(OrderItem entity) {
        productId = entity.geProduct().getId();
        name = entity.geProduct().getName();
        price = entity.getPrice();
        quantity = entity.getQuantity();
        imgUrl = entity.geProduct().getImgUrl();
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getSubtotal(){
        return quantity * price;
    }

}
