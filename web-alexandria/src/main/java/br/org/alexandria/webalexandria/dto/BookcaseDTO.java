package br.org.alexandria.webalexandria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookcaseDTO {

  private Long id;
  private BookDTO book;
  private String comments;
  private Boolean read;
  private String gender;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public BookDTO getBook () {
    return book;
  }

  public void setBook (BookDTO book) {
    this.book = book;
  }

  public String getComments () {
    return comments;
  }

  public void setComments (String comments) {
    this.comments = comments;
  }

  public Boolean getRead () {
    return read;
  }

  public void setRead (Boolean read) {
    this.read = read;
  }

  public String getGender () {
    return gender;
  }

  public void setGender (String gender) {
    this.gender = gender;
  }
}