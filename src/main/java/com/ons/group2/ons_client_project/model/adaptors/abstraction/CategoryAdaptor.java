package com.ons.group2.ons_client_project.model.adaptors.abstraction;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;

public interface CategoryAdaptor {
    Category createNewCategory(NewCategoryDto newCategoryDto);
}
