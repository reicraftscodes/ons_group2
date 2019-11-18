package com.ons.group2.ons_client_project.repository;

import com.ons.group2.ons_client_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<User, Integer> {

}
