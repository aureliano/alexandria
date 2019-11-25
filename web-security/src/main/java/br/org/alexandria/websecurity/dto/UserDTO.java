package br.org.alexandria.websecurity.dto;

import java.util.Set;

public class UserDTO {

  private Long id;
  private String name;
  private String password;
  private String confirmPassword;
  private Set<String> roles;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
  }

  public String getConfirmPassword () {
    return confirmPassword;
  }

  public void setConfirmPassword (String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public Set<String> getRoles () {
    return roles;
  }

  public void setRoles (Set<String> roles) {
    this.roles = roles;
  }
}