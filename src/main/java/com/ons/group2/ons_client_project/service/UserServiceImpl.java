package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
//        user.setStatus("VERIFIED");
        userRepository.save(user);
    }

    @Override
    public boolean isUserAlreadyPresent(User user) {
        return false;
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void changePassword(User user, String newPassword) {
        user.setPassword(newPassword);

        userRepository.save(user);
    }
}
