package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> getByName(String name);

    List<Category> getByParent(Category parentCategory);
}
