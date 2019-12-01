package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long > {
    User findByEmail(String email);
}