package com.example.discussion.services;

import com.example.discussion.models.Course;
import com.example.discussion.models.User;
import com.example.discussion.repositories.CoursesRepository;
import com.example.discussion.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private CoursesRepository coursesRepository;

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

  public ResponseEntity updateUser(Long id, User user){
    User userToBeUpdated = usersRepository.findById(id).get();

    userToBeUpdated.setUsername(user.getUsername());
    userToBeUpdated.setPassword(user.getPassword());
    userToBeUpdated.setFullName(user.getFullName());
    userToBeUpdated.setAddress(user.getAddress());

    usersRepository.save(userToBeUpdated);
    return new ResponseEntity("User updated succesfully", HttpStatus.OK);
  }

  public Optional<User> findByUsername(String username){
    return Optional.ofNullable(usersRepository.findByUsername(username));
  }

  public ResponseEntity enrollCourse(String courseTitle, Long user_id){
    User enrolee = usersRepository.findById(user_id).get();
    Course courseToBeEnrolled = coursesRepository.findByTitle(courseTitle);

    Set<Course> enrolledCourse =enrolee.getCourses();
    Set<User> enrolledUsers = courseToBeEnrolled.getUser();

    enrolledCourse.add(courseToBeEnrolled);
    enrolledUsers.add(enrolee);


    enrolee.setCourses(enrolledCourse);
    courseToBeEnrolled.setUser(enrolledUsers);

    usersRepository.save(enrolee);
    coursesRepository.save(courseToBeEnrolled);
    return new ResponseEntity(enrolee.getUsername() + " successfully enrolled in " + courseTitle, HttpStatus.OK);

//    return new ResponseEntity(enrolledCourse,HttpStatus.OK);
  }
}
