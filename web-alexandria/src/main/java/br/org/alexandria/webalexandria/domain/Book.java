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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

  @Id
  @SequenceGenerator(name = "books_seq_gen", sequenceName = "books_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_seq_gen")
  private long id;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "synopsis", nullable = true, length = 500)
  private String synopsis;

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

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "writers_books", joinColumns = {
      @JoinColumn(name = "book_id") }, inverseJoinColumns = {
          @JoinColumn(name = "writer_id") })
  private List<Writer> writers;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
  @JoinColumn(name = "book_id")
  private List<Image> images;

  public long getId () {
    return id;
  }

  public void setId (long id) {
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

  public List<Writer> getWriters () {
    return writers;
  }

  public void setWriters (List<Writer> writers) {
    this.writers = writers;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((language == null) ? 0 : language.hashCode ());
    result = prime * result + ((title == null) ? 0 : title.hashCode ());
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
    Book other = (Book) obj;
    if (language == null) {
      if (other.language != null)
        return false;
    } else if (!language.equals (other.language)) {
      return false;
    }
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals (other.title)) {
      return false;
    }
    return true;
  }
}