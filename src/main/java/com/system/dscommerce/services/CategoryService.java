package com.system.dscommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.dscommerce.dtos.CategoryDTO;
import com.system.dscommerce.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        var categories = repository.findAll();
        return categories.stream().map(cat -> new CategoryDTO(cat)).toList();
    }
}
