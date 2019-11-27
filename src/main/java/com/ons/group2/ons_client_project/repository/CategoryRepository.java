package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);

    @Query("select c from Category c where c.parentCategory is null order by c.name")
    List<Category> findAllTopLevel();
}
