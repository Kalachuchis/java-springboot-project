package com.example.discussion.services;

import com.example.discussion.config.JwtToken;
import com.example.discussion.models.Course;
import com.example.discussion.models.User;
import com.example.discussion.repositories.CoursesRepository;
import com.example.discussion.repositories.UsersRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private CoursesRepository coursesRepository;

  @Autowired
  JwtToken jwtToken;

  public ResponseEntity createUser (User user){
    for(User indivUser: usersRepository.findAll()){
      if(user.getUsername().equalsIgnoreCase(indivUser.getUsername())) return new ResponseEntity("User already exists", HttpStatus.CONFLICT);
    }
    usersRepository.save(user);
    return new ResponseEntity("User created successfully", HttpStatus.CREATED);
  }
  public Iterable<User> getUser(){
    return usersRepository.findAll();
  }
  public ResponseEntity deleteUser(Long id){
    usersRepository.deleteById(id);
    return new ResponseEntity("User deleted successfully", HttpStatus.OK);
  }

  public ResponseEntity updateUser(Long id, User user, String stringToken){
    User userToBeUpdated = usersRepository.findById(id).get();
    String userToBeUpdatedName = userToBeUpdated.getUsername();
    String authenticatedUsername = jwtToken.getUsernameFromToken(stringToken);

    if(!authenticatedUsername.equalsIgnoreCase(userToBeUpdatedName)) {
      return new ResponseEntity("You are not authorized to edit this user.", HttpStatus.UNAUTHORIZED);
    }

    String password = user.getPassword();
    String encodedPassword = new BCryptPasswordEncoder().encode(password);
    userToBeUpdated.setUsername(user.getUsername());
    userToBeUpdated.setPassword(encodedPassword);
    userToBeUpdated.setFullName(user.getFullName());
    userToBeUpdated.setAddress(user.getAddress());

    usersRepository.save(userToBeUpdated);
    return new ResponseEntity("User updated succesfully", HttpStatus.OK);
  }

  public Optional<User> findByUsername(String username){
    return Optional.ofNullable(usersRepository.findByUsername(username));
  }

  public ResponseEntity enrollCourse(String courseTitle, Long user_id, String stringToken){
    User enrollee = usersRepository.findById(user_id).get(); // finds user to be enrolled
    Course courseToBeEnrolled = coursesRepository.findByTitle(courseTitle); // finds specified course
    String enrolleeName = enrollee.getUsername();
    String authenticatedUsername = jwtToken.getUsernameFromToken(stringToken);

    if(!authenticatedUsername.equalsIgnoreCase(enrolleeName)) {
      return new ResponseEntity("You are not authorized to enroll to this course.", HttpStatus.UNAUTHORIZED);
    }

    if (!courseToBeEnrolled.getActive()) return new ResponseEntity("Sorry, this course is archived and cannot be enrolled", HttpStatus.CONFLICT);

    Set<Course> enrolledCourse =enrollee.getCourses(); // gets the courses property from the user
    Set<User> enrolledUsers = courseToBeEnrolled.getUser(); // gets the user property form the courses

    enrolledCourse.add(courseToBeEnrolled);
    enrollee.setCourses(enrolledCourse);
    usersRepository.save(enrollee);

//    enrolledUsers.add(enrollee);
//    courseToBeEnrolled.setUser(enrolledUsers);
//    coursesRepository.save(courseToBeEnrolled);

    return new ResponseEntity(enrollee.getUsername() + " successfully enrolled in " + courseTitle, HttpStatus.OK);
  }

  public ResponseEntity getEnrolledCourse (Long id, String stringToken){
    User usersForFindingCourse = usersRepository.findById(id).get();

    User userToBeUpdated = usersRepository.findById(id).get();
    String userToBeUpdatedName = userToBeUpdated.getUsername();
    String authenticatedUsername = jwtToken.getUsernameFromToken(stringToken);

    if(!authenticatedUsername.equalsIgnoreCase(userToBeUpdatedName)) {
      return new ResponseEntity("You are not authorized to see this user's courses.", HttpStatus.UNAUTHORIZED);
    }

    Set<Course> enrolledCourses = usersForFindingCourse.getCourses();
    return new ResponseEntity (enrolledCourses, HttpStatus.OK);
  }
}
