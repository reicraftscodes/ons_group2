package com.ons.group2.ons_client_project.controllers.rest;

import com.ons.group2.ons_client_project.model.Category;
import com.ons.group2.ons_client_project.model.dto.category.NewCategoryDto;
import com.ons.group2.ons_client_project.service.CategoryService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity createCategory(@RequestBody @Valid NewCategoryDto newCategoryDto) {
        try {
            var category = categoryService.create(newCategoryDto);

            return ResponseEntity.ok(category);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(
                    String.format("Duplicate entry for '%s'.", newCategoryDto.getName())
            );
        }
    }

    @GetMapping("")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/topLevel")
    public ResponseEntity allTopLevel() {
        return ResponseEntity.ok(categoryService.getAllTopLevel());
    }

    @GetMapping("/{name}")
    public ResponseEntity categoryByName(@PathVariable String name) {
        var category = categoryService.getByName(name);
        if(category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category.get());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity categoryById(@PathVariable Integer id) {
        var category = categoryService.getById(id);
        if(category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category.get());
    }
}