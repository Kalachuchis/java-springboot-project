package com.example.discussion.controllers;

import com.example.discussion.models.User;
import com.example.discussion.services.UserService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;

@RestController
@CrossOrigin
public class UserController {
  @Autowired
  UserService userService;

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
  public ResponseEntity<Object> updateUser(@PathVariable Long user_id, @RequestBody User user){
    return userService.updateUser(user_id, user);
  }

  @RequestMapping(value = "/enroll_course/{user_id}/{courseTitle}", method = RequestMethod.PUT)
  public ResponseEntity<Object> enrollCourse(@PathVariable(value ="courseTitle") String courseTitle, @PathVariable(value = "user_id") Long user_id){
    return  userService.enrollCourse(courseTitle, user_id);
  }
}
