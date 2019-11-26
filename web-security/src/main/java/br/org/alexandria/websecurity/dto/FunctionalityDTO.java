package br.org.alexandria.websecurity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FunctionalityDTO {

  private Long id;
  private String functionality;
  private String description;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getFunctionality () {
    return functionality;
  }

  public void setFunctionality (String functionality) {
    this.functionality = functionality;
  }

  public String getDescription () {
    return description;
  }

  public void setDescription (String description) {
    this.description = description;
  }
}