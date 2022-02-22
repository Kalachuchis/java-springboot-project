package com.example.discussion.services;

import com.example.discussion.models.Course;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CourseService {
  ResponseEntity createCourse(Course course);
  Iterable<Course> getCourse();
  ResponseEntity deleteCourse(Long id);
  ResponseEntity updateCourse(Long id, Course course);
  ResponseEntity archiveCourse(Long id);
  Optional<Course> findByTitle(String title);


}
