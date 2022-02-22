package com.example.discussion.services;

import com.example.discussion.models.Course;
import com.example.discussion.repositories.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.ResultSetSupportingSqlParameter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

  @Autowired
  CoursesRepository coursesRepository;

  // Create course
  public ResponseEntity createCourse(Course course){

    for(Course indivCourse: coursesRepository.findAll()) {
      if (course.getTitle().equalsIgnoreCase(indivCourse.getTitle()))
        return new ResponseEntity("Duplicate course found", HttpStatus.CONFLICT);

    }
    coursesRepository.save(course);
    return new ResponseEntity<>("Course created successfully", HttpStatus.CREATED);
  }

  // Get all courses
  public Iterable<Course> getCourse(){
    return coursesRepository.findAll();
  }

  // Delete course by ID
  public ResponseEntity deleteCourse(Long id){

    coursesRepository.deleteById(id);
    return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
  }

  // Update course by ID
  public ResponseEntity updateCourse(Long id, Course course){
    Course courseToBeUpdated = coursesRepository.findById(id).get();

    courseToBeUpdated.setTitle(course.getTitle());
    courseToBeUpdated.setDescription(course.getDescription());
    courseToBeUpdated.setLength(course.getLength());

    coursesRepository.save(courseToBeUpdated);
    return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
  }

  // Archive course
  public ResponseEntity archiveCourse(Long id){
    // find course to archive
    Course courseForArchiving = coursesRepository.findById(id).get();
    // update active attribute
    courseForArchiving.setActive(!courseForArchiving.getActive());
    // save course
    coursesRepository.save(courseForArchiving);

    return new ResponseEntity("Course with id " + id + " was updated to" + courseForArchiving.getActive(), HttpStatus.OK);
  }

  public Optional<Course> findByTitle(String title){
    return Optional.ofNullable(coursesRepository.findByTitle(title));
  }
}
