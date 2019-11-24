package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.adaptors.CategoryAdaptor;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;
import com.ons.group2.ons_client_project.repository.CategoryRepository;
import com.ons.group2.ons_client_project.service.CategoryService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryAdaptor categoryAdaptor;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryAdaptor categoryAdaptor) {
        this.categoryRepository = categoryRepository;
        this.categoryAdaptor = categoryAdaptor;
    }


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllByParent(Category parentCategory) {
        return categoryRepository.findAllByParentCategory(parentCategory);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    public Category create(NewCategoryDto newCategoryDto) {
        var category = categoryAdaptor.createNewCategory(newCategoryDto);

        if(newCategoryDto.getParentCategoryId() != null) {
            Optional<Category> parentCategory = getById(newCategoryDto.getParentCategoryId());

            category.setParentCategory(parentCategory.get());
        }

        return save(category);
    }
}
