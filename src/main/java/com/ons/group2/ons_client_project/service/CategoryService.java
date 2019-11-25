package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {


    Optional<Category> getByName(String name);
    Optional<Category> getById(Integer id);

    List<Category> getAll();
    List<Category> getAllTopLevel();

    Category save(Category category);
    Category create(NewCategoryDto newCategoryDto);
}
