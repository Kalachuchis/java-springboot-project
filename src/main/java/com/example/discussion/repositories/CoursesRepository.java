package com.example.discussion.repositories;

import com.example.discussion.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Object> {
  Course findByTitle (String title);
}
