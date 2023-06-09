package com.system.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dscommerce.dtos.ProductDTO;
import com.system.dscommerce.entities.Product;
import com.system.dscommerce.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        var product = repository.findById(id).get();
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        var products = repository.findAll(pageable);
        return products.map(p -> new ProductDTO(p));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        var p = new Product();
        dtoToProduct(dto, p);
        p = repository.save(p);
        return new ProductDTO(p);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            var product = repository.getReferenceById(id);
            dtoToProduct(dto, product);
            return new ProductDTO(repository.save(product));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("erro");
        }
    }

    private void dtoToProduct(ProductDTO dto, Product p) {
        p.setName(dto.name());
        p.setDescription(dto.description());
        p.setPrice(dto.price());
        p.setImgUrl(dto.imgUrl());
    }
}
