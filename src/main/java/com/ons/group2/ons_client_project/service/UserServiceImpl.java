package com.ons.group2.ons_client_project.service;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.UserRepository;
import com.ons.group2.ons_client_project.utils.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder;
    private static final String defaultUserProfilePicURL = "/images/profile_page/default_profile.jpeg";
    private static final String uploadWebLocationBase = "/images/user/profile/";

    @Value("${upload.path}")
    private String basePath;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
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
    public URI changeProfilePicture(User user, MultipartFile newImg) {

        String filename = StringUtils.cleanPath(newImg.getOriginalFilename());
        try {
            if (newImg.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // this is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = newImg.getInputStream()) {
                var path = basePath + user.getId() + "/";
                var finalPath = Paths.get(path).resolve(filename);

                Files.createDirectories(finalPath.getParent());
                Files.copy(inputStream, finalPath,
                        StandardCopyOption.REPLACE_EXISTING);

                // remove previous profile picture
                Files.deleteIfExists(Paths.get("src/main/resources/static/" + user.getProfileUrl()));

                // Set the url in the db
                user.setProfileUrl(uploadWebLocationBase + user.getId() + "/" + filename);
                userRepository.save(user);

                // return the created url
                return URI.create(uploadWebLocationBase + filename);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }
}
