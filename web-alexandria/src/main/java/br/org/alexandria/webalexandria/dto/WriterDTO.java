package br.org.alexandria.webalexandria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class WriterDTO {

  private Long id;
  private String fullName;
  private String nickName;
  private String nationality;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public String getFullName () {
    return fullName;
  }

  public void setFullName (String fullName) {
    this.fullName = fullName;
  }

  public String getNickName () {
    return nickName;
  }

  public void setNickName (String nickName) {
    this.nickName = nickName;
  }

  public String getNationality () {
    return nationality;
  }

  public void setNationality (String nationality) {
    this.nationality = nationality;
  }
}