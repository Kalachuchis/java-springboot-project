package com.example.discussion.models;

import java.io.Serializable;

public class JwtRequest implements Serializable {


  // Properties
  private static final long serialVersionUID = 5926468583005150707L;

  private String username;
  private String password;

  public JwtRequest(){
  }

  // Constructors
  public JwtRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  // Getters and Setters
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
}
