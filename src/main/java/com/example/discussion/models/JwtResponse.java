package com.example.discussion.models;

import java.io.Serializable;

public class JwtResponse implements Serializable {

  // Properties
  private static final long serialVersionUID = -8091879091924046844L;


  private String jwtToken;

  // Contrusctors

  public JwtResponse() {}

  public JwtResponse(String jwttoken) {
    this.jwtToken = jwttoken;
  }

  public String getJwttoken() {
    return jwtToken;
  }
}
