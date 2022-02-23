package com.example.discussion.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Set;

@Entity
@Table(name="courses")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "title")
public class Course {

  @Id
  @GeneratedValue
  private Long id;

  // Properties
  @Column
  private String title;
  @Column
  private String description;
  @Column
  private Long length;
  @Column
  private BigDecimal price;
  @Column
  private Boolean active = true;


  // Foreign key
  @ManyToMany(mappedBy = "courses")
//  @JsonBackReference
  @JsonIgnore
  private Set<User> user;

  // Constructors
  public Course (){}

  public Course(String title, String description, Long length, BigDecimal price, User user, Boolean active) {
    this.title = title;
    this.description = description;
    this.length = length;
    this.price = price;
    this.active = active;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getLength() {
    return length;
  }

  public void setLength(Long length) {
    this.length = length;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Set<User> getUser() {
    return user;
  }

  public void setUser(Set<User> user) {
    this.user = user;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }
}
