package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.adaptors.CategoryAdaptor;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;
import com.ons.group2.ons_client_project.repository.CategoryRepository;
import com.ons.group2.ons_client_project.repository.UserSkillsRepository;
import com.ons.group2.ons_client_project.service.CategoryService;
import com.ons.group2.ons_client_project.service.UserSkillService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryAdaptor categoryAdaptor;
    private final UserSkillsRepository userSkillsRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryAdaptor categoryAdaptor, UserSkillsRepository userSkillsRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryAdaptor = categoryAdaptor;
        this.userSkillsRepository = userSkillsRepository;
    }


    /**
     * @return all the categories in the db.
     */
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    /**
     * @return all the top level categories (categories which are not subcategories)
     */
    @Override
    public List<Category> getAllTopLevel() {
        return categoryRepository.findAllTopLevel();
    }

    /**
     * Retrieve a category by the specified name
     *
     * @param name the name of the category
     * @return the category found by the specified name
     */
    @Override
    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    /**
     * Retrieve a category by the specified id
     *
     * @param id the id of the category
     * @return the category found by the specified id
     */
    @Override
    public Optional<Category> getById(Integer id) {
        return categoryRepository.findById(id);
    }

    /**
     * Save a category into the database.
     *
     * @param category the new category
     * @return the saved category
     */
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }


    /**
     * Create a new category in the database from a Dto
     *
     * @param newCategoryDto the specified dto
     * @return the newly created category
     */
    public Category create(NewCategoryDto newCategoryDto) {
        var category = categoryAdaptor.createNewCategory(newCategoryDto);

        return save(category);
    }

    /**
     * Remove the specified category from the database
     *
     * @param category the specified category
     */
    @Override
    public void remove(Category category) {
        if(category == null) return;

        var skillsWithCategory = userSkillsRepository.findAllByCategory_Id(category.getId());

        for(var skill : skillsWithCategory) {
            skill.setCategory(null);

            userSkillsRepository.save(skill);
        }

        categoryRepository.delete(category);
    }

    /**
     * Remove a category by the specified id
     *
     * @param categoryId the specified id of the category
     */
    @Override
    public void removeById(Integer categoryId) {
        var category = getById(categoryId);

        category.ifPresent(this::remove);
    }
}
