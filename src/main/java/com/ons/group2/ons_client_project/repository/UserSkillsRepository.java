package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSkillsRepository extends JpaRepository<UserSkill, Integer> {
    Optional<UserSkill> findById(Integer id);

    List<UserSkill> findAllByUser_Id(Integer userId);
    List<UserSkill> findAllByCategory_Id(Integer categoryId);

    void deleteByIdAndUser_Id(Integer skillId, Integer userId);
}
