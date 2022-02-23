package com.example.discussion.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Users")
//@JsonIdentityInfo(
//    generator = ObjectIdGenerators.PropertyGenerator.class,
//    property = "fullName")
public class User {

  @Id
  @GeneratedValue
  private Long Id;

  // Properties
  @Column
  private String username;
  @Column
  private String password;
  @Column
  private String fullName;
  @Column
  private String address;

  // Foreign Key
  // Makes User_Courses table to join users and courses
  @ManyToMany
  @JsonManagedReference
  @JoinTable(
      name = "Users_Courses",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id")
  )
  private Set<Course> courses;



  // Constructor
  public User(){}

  public User(String username, String password, String fullName, String address) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.address = address;
  }

  // Getters and Setters
  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Set<Course> getCourses() {
    return courses;
  }

  public void setCourses(Set<Course> courses) {
    this.courses = courses;
  }
}
