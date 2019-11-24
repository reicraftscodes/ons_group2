package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Optional<Category> getByName(String name);

    List<Category> getAllByParent(Category parentCategory);
}
