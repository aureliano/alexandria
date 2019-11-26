package br.org.alexandria.websecurity.dto;

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