package br.org.alexandria.webalexandria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ImageDTO {

  private Long id;
  private String filePath;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getFilePath () {
    return filePath;
  }

  public void setFilePath (String filePath) {
    this.filePath = filePath;
  }
}