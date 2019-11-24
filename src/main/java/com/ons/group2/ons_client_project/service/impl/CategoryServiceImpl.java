package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.repository.CategoryRepository;
import com.ons.group2.ons_client_project.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllByParent(Category parentCategory) {
        return categoryRepository.findAllByParentCategory(parentCategory);
    }
}
