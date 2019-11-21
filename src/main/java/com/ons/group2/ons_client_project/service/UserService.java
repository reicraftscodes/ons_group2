package com.ons.group2.ons_client_project.service;


import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}