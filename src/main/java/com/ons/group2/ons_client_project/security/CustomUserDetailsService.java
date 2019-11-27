package com.ons.group2.ons_client_project.security;

import com.ons.group2.ons_client_project.model.User;
import com.ons.group2.ons_client_project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  private final PasswordEncoder encoder;

  public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {

    System.out.println("password encoded = " + encoder.encode("password"));


    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException(username);
    } else {
      System.out.println("User = " + user);
      return new UserPrincipal(user, user.getRoles());
    }
  }
}
