package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);
    boolean isUserAlreadyPresent(User user);
    Optional<User> findById(Integer userId);

    void changePassword(User user, String newPassword);
    URI changeProfilePicture(User user, MultipartFile newImg) throws IOException;
}
