package com.example.discussion.controllers;

import com.example.discussion.exceptions.UserException;
import com.example.discussion.models.User;
import com.example.discussion.repositories.UsersRepository;
import com.example.discussion.services.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  UsersRepository usersRepository;

  @GetMapping("/test")
  public String hello(){
    return "hello world";
  }

  @RequestMapping(value ="/users", method = RequestMethod.POST)
  public ResponseEntity<Object> createUser (@RequestBody User user){
    return userService.createUser(user);
  }

  @RequestMapping(value ="/users", method = RequestMethod.GET)
  public ResponseEntity<Object> getUser(){
    return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
  }


  @RequestMapping(value ="/users/{user_id}", method = RequestMethod.DELETE)
  public ResponseEntity<Object> deleteUser(@PathVariable Long user_id){
    return  userService.deleteUser(user_id);
  }

  @RequestMapping(value ="/users/{user_id}", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateUser(@PathVariable Long user_id, @RequestBody User user, @RequestHeader(value = "Authorization") String stringToken){
    return userService.updateUser(user_id, user, stringToken);
  }

  @RequestMapping(value = "/enroll_course/{user_id}/{courseTitle}", method = RequestMethod.PUT)
  public ResponseEntity<Object> enrollCourse(@PathVariable(value ="courseTitle") String courseTitle, @PathVariable(value = "user_id") Long user_id, @RequestHeader(value = "Authorization") String stringToken){
    return  userService.enrollCourse(courseTitle, user_id, stringToken);
  }


  // User registration
  @RequestMapping(value = "/users/register", method=RequestMethod.POST)
  public ResponseEntity<Object> register(@RequestBody Map<String, String> body) throws UserException {

    String username = body.get("username");
    String address = body.get("address");
    String fullname = body.get("fullName");

    if(!userService.findByUsername(username).isEmpty()){
      throw new UserException("Username already exists");
    } else{
      String password = body.get("password");
      String encodedPassword = new BCryptPasswordEncoder().encode(password);

      User newUser = new User(username, encodedPassword, fullname, address);

      userService.createUser(newUser);

      return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
  }
}
