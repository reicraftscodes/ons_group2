package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ons.group2.ons_client_project.utils.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String defaultUserProfilePicURL = "/images/profile_page/default_profile.jpeg";
    private static final String uploadWebLocationBase = "/images/user/profile/";

    @Value("${upload.path}")
    private String basePath;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
//        user.setStatus("VERIFIED");
        user.setProfileUrl(defaultUserProfilePicURL);

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

    @Override
    public void changeProfilePicture(User user, MultipartFile newImg) {

        String filename = StringUtils.cleanPath(newImg.getOriginalFilename());
        try {
            if (newImg.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = newImg.getInputStream()) {
                var path = basePath + user.getId() + "/profile/";

                Files.copy(inputStream, Paths.get(basePath).resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);

                user.setProfileUrl(uploadWebLocationBase + filename);
                userRepository.save(user);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }
}
