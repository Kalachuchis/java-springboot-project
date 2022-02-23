package com.example.discussion.services;

import com.example.discussion.models.Course;
import com.example.discussion.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
  ResponseEntity createUser (User user);
  Iterable<User> getUser();
  ResponseEntity deleteUser(Long id);
  ResponseEntity updateUser(Long id, User user, String stringToken);
  ResponseEntity enrollCourse(String courseTitle, Long user_id, String stringToken);
  Optional<User> findByUsername(String username);
//  ResponseEntity getEnrolledCourses
}
