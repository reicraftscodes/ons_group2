package com.ons.group2.ons_client_project.model.adaptors;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdaptor implements com.ons.group2.ons_client_project.model.adaptors.abstraction.CategoryAdaptor {
    public Category createNewCategory(NewCategoryDto newCategoryDto) {
        return new Category(
                null,
                newCategoryDto.getName(),
                null,
                newCategoryDto.getParentCategoryId()
        );
    }
}
