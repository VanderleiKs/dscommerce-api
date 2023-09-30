package com.system.dscommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.system.dscommerce.dtos.CategoryDTO;
import com.system.dscommerce.dtos.ProductDTO;
import com.system.dscommerce.dtos.ProductMinDTO;
import com.system.dscommerce.entities.Category;
import com.system.dscommerce.entities.Product;
import com.system.dscommerce.repositories.CategoryRepository;
import com.system.dscommerce.repositories.ProductRepository;
import com.system.dscommerce.services.exceptions.DatabaseExceptionService;
import com.system.dscommerce.services.exceptions.NotFoundExceptionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        var product = repository.findById(id).orElseThrow(
                () -> new NotFoundExceptionService("Produto não encontrado"));
        return new ProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable) {
        var products = repository.findAllFilterName(name, pageable);
        return products.map(p -> new ProductMinDTO(p));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        var p = new Product();
        dtoToProduct(dto, p);
        p = repository.save(p);

        for (Category cat : p.getCategories()) {
            cat.setName(categoryRepository.findById(cat.getId()).get().getName());
        }
        /* List<Category> cats = new ArrayList<>();
        p.getCategories().forEach(cat -> cats.add(categoryRepository.findById(cat.getId()).get()));
        p.getCategories().clear();
        for (Category cat : cats) {
            p.getCategories().add(cat);
        } */
        
        return new ProductDTO(p);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            var product = repository.getReferenceById(id);
            dtoToProduct(dto, product);
            return new ProductDTO(repository.save(product));
        } catch (EntityNotFoundException e) {
            throw new NotFoundExceptionService("Produto não encontrado para ser atualizado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new NotFoundExceptionService("Produto não encontrado para ser deletado");
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseExceptionService("Produto não pode ser deletado");
        }
    }

    private void dtoToProduct(ProductDTO dto, Product p) {
        p.setName(dto.name());
        p.setDescription(dto.description());
        p.setPrice(dto.price());
        p.setImgUrl(dto.imgUrl());
        p.getCategories().clear();
        p.getCategories().clear();
        for (CategoryDTO catDto : dto.categories()) {
            Category category = new Category();
            category.setId(catDto.id());
            category.setName(catDto.name());
            p.getCategories().add(category);
        }
    }
}
