package br.org.alexandria.webalexandria.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BookDTO {

  private Long id;
  private String title;
  private String synopsis;
  private String publishingCompany;
  private Integer edition;
  private Integer year;
  private String language;
  private Integer pages;
  private Double height;
  private Double width;
  private Double weight;
  private List<WriterDTO> writers;
  private List<ImageDTO> images;

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

  public String getPublishingCompany () {
    return publishingCompany;
  }

  public void setPublishingCompany (String publishingCompany) {
    this.publishingCompany = publishingCompany;
  }

  public Integer getEdition () {
    return edition;
  }

  public void setEdition (Integer edition) {
    this.edition = edition;
  }

  public Integer getYear () {
    return year;
  }

  public void setYear (Integer year) {
    this.year = year;
  }

  public String getLanguage () {
    return language;
  }

  public void setLanguage (String language) {
    this.language = language;
  }

  public Integer getPages () {
    return pages;
  }

  public void setPages (Integer pages) {
    this.pages = pages;
  }

  public Double getHeight () {
    return height;
  }

  public void setHeight (Double height) {
    this.height = height;
  }

  public Double getWidth () {
    return width;
  }

  public void setWidth (Double width) {
    this.width = width;
  }

  public Double getWeight () {
    return weight;
  }

  public void setWeight (Double weight) {
    this.weight = weight;
  }

  public List<WriterDTO> getWriters () {
    return writers;
  }

  public void setWriters (List<WriterDTO> writers) {
    this.writers = writers;
  }

  public List<ImageDTO> getImages () {
    return images;
  }

  public void setImages (List<ImageDTO> images) {
    this.images = images;
  }
}