package br.org.alexandria.webalexandria.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookDTO {

  private Long id;
  private String title;
  private String synopsis;
  private List<WriterDTO> writers;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getTitle () {
    return title;
  }

  public void setTitle (String title) {
    this.title = title;
  }

  public String getSynopsis () {
    return synopsis;
  }

  public void setSynopsis (String synopsis) {
    this.synopsis = synopsis;
  }

  public List<WriterDTO> getWriters () {
    return writers;
  }

  public void setWriters (List<WriterDTO> writers) {
    this.writers = writers;
  }
}