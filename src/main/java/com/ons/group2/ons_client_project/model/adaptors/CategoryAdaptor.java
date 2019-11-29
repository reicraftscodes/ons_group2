package com.ons.group2.ons_client_project.model.adaptors;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;
import com.ons.group2.ons_client_project.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryAdaptor implements com.ons.group2.ons_client_project.model.adaptors.abstraction.CategoryAdaptor {
    private final CategoryRepository categoryRepository;

    public CategoryAdaptor(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createNewCategory(NewCategoryDto newCategoryDto) {
        var parentCategoryOpt =
                newCategoryDto.getParentCategoryId() == null ?
                        java.util.Optional.<Category>empty() :
                        categoryRepository.findById(newCategoryDto.getParentCategoryId());

        return new Category(
                null,
                newCategoryDto.getName(),
                null,
                parentCategoryOpt.orElse(null)
        );

    }

}
