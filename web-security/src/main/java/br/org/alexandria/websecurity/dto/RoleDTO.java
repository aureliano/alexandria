package br.org.alexandria.websecurity.dto;

import java.util.Set;

public class RoleDTO {

  private Long id;
  private String role;
  private String description;
  private Set<FunctionalityDTO> functionalities;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getRole () {
    return role;
  }

  public void setRole (String role) {
    this.role = role;
  }

  public String getDescription () {
    return description;
  }

  public void setDescription (String description) {
    this.description = description;
  }

  public Set<FunctionalityDTO> getFunctionalities () {
    return functionalities;
  }

  public void setFunctionalities (Set<FunctionalityDTO> functionalities) {
    this.functionalities = functionalities;
  }
}