package com.example.discussion.services;

import com.example.discussion.models.User;
import com.example.discussion.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class JwtUserDetailService implements UserDetailsService {

  @Autowired
  private UsersRepository userRepository;

  // Register the user credentials in the application
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    // Check if username exists and get the user if it does
    User user = userRepository.findByUsername(username);
    // If no user found throw exception
    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    // Store user credentials in User object for spring security
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
  }
}
