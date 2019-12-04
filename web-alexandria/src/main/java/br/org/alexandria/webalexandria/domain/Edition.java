package br.org.alexandria.webalexandria.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "editions")
public class Edition {

  @Id
  @SequenceGenerator(name = "editions_seq_gen", sequenceName = "editions_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "editions_seq_gen")
  private Long id;

  @Column(name = "publishing_company", nullable = true, length = 50)
  private String publishingCompany;

  @Column(name = "edition", nullable = true)
  private Integer edition;

  @Column(name = "year", nullable = true)
  private Integer year;

  @Column(name = "language", nullable = false, length = 20)
  private String language;

  @Column(name = "pages", nullable = true)
  private Integer pages;

  @Column(name = "height", nullable = true)
  private Double height;

  @Column(name = "width", nullable = true)
  private Double width;

  @Column(name = "weight", nullable = true)
  private Double weight;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "edition")
  private List<Image> images;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
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

  public Book getBook () {
    return book;
  }

  public void setBook (Book book) {
    this.book = book;
  }

  public List<Image> getImages () {
    return images;
  }

  public void setImages (List<Image> images) {
    this.images = images;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((edition == null) ? 0 : edition.hashCode ());
    result = prime * result + ((language == null) ? 0 : language.hashCode ());
    return result;
  }

  @Override
  public boolean equals (Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass () != obj.getClass ())
      return false;
    Edition other = (Edition) obj;
    if (edition == null) {
      if (other.edition != null)
        return false;
    } else if (!edition.equals (other.edition))
      return false;
    if (language == null) {
      if (other.language != null)
        return false;
    } else if (!language.equals (other.language))
      return false;
    return true;
  }
}