package com.ons.group2.ons_client_project.service.impl;

import com.ons.group2.ons_client_project.model.Role;
import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.model.dto.account.UpdateUserInfoDto;
import com.ons.group2.ons_client_project.repository.UserRepository;
import com.ons.group2.ons_client_project.service.UserService;
import com.ons.group2.ons_client_project.utils.StorageException;
import com.ons.group2.ons_client_project.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String defaultUserProfilePicURL = "/images/profile_page/default_profile.jpeg";
    private static final String uploadWebLocationBase = "/images/user/profile/";

    @Value("${upload.path}")
    private String basePath;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(UpdateUserInfoDto userInfoDto) {
        if(userInfoDto.getUserId() == null)
            throw new IllegalArgumentException("User id must not be null.");

        Optional<User> userToUpdateOpt = userRepository.findById(userInfoDto.getUserId());

        if(userToUpdateOpt.isEmpty())
            throw new UsernameNotFoundException("Cannot update user. User not found.");

        var user = userToUpdateOpt.get();

        user.setEmail(userInfoDto.getEmail());
        user.setFirstName(userInfoDto.getFirstName());
        user.setLastName(userInfoDto.getLastName());

        return userRepository.save(user);
    }

    public User newUser(UserRegistrationDto registration) {
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setProfileUrl(defaultUserProfilePicURL);

        return userRepository.save(user);
    }

    /** Reset features*/
    @Override
    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public URI changeProfilePicture(User user, MultipartFile newImg) {

        String filename = StringUtils.cleanPath(Objects.requireNonNull(newImg.getOriginalFilename()));
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
                if(!user.getProfileUrl().equals(defaultUserProfilePicURL)) // don't delete default profile
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