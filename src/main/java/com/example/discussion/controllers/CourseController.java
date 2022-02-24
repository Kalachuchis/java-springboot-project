package com.example.discussion.controllers;

import com.example.discussion.models.Course;
import com.example.discussion.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CourseController {
  @Autowired
  CourseService courseService;

//  @RequestMapping(value = "/courses", method = RequestMethod.POST)
  @PostMapping("/courses")
  public ResponseEntity<Object> createCourse(@RequestBody Course course){
    return courseService.createCourse(course);
  }

  @GetMapping("/courses")
  public ResponseEntity<Object> getCourse(){
    return new ResponseEntity<>(courseService.getCourse(), HttpStatus.OK);
  }

  @GetMapping("/available_courses")
  public ResponseEntity<Object> getAvailableCourses(){
    return (courseService.getAvailableCourses());
  }

  @DeleteMapping("/courses/{course_id}")
  public ResponseEntity<Object> deleteCourse(@PathVariable Long course_id){
    return courseService.deleteCourse(course_id);
  }

  @GetMapping("/courses/{coursename}")
  public ResponseEntity<Object> getCourseByName(@PathVariable String coursename){
    return new ResponseEntity<>(courseService.findByTitle(coursename), HttpStatus.OK);
  }
  @PutMapping("/courses/{course_id}")
  public ResponseEntity<Object> updateCourse(@PathVariable Long course_id, @RequestBody Course course){
    return courseService.updateCourse(course_id, course);
  }

  @PutMapping("/courses/archive/{course_id}")
  public ResponseEntity<Object> archiveCourse(@PathVariable Long course_id){
    return courseService.archiveCourse(course_id);
  }

  @GetMapping("/enrolled_users/{course_id}")
  public ResponseEntity<Object> getEnrolledUsers(@PathVariable Long course_id){
//    return new ResponseEntity<>("sup",HttpStatus.I_AM_A_TEAPOT);
    return courseService.getEnrolledUsers(course_id);
  }

}
