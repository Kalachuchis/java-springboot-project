package com.example.discussion.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table(name="courses")
public class Course {

  @Id
  @GeneratedValue
  private Long id;

  private String title;
  private String description;
  private Time length;
  private BigDecimal price;

  @ManyToMany()
  private User user;

  // Constructors
  public Course (){}

  public Course(String title, String description, Time length, BigDecimal price, User user) {
    this.title = title;
    this.description = description;
    this.length = length;
    this.price = price;
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

  public Time getLength() {
    return length;
  }

  public void setLength(Time length) {
    this.length = length;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
