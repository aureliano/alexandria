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
  private Long id;

  @Column(name = "title", nullable = false, length = 100)
  private String title;

  @Column(name = "synopsis", nullable = true, length = 500)
  private String synopsis;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "writers_books", joinColumns = {
      @JoinColumn(name = "book_id") }, inverseJoinColumns = {
          @JoinColumn(name = "writer_id") })
  private List<Writer> writers;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
  private List<Edition> editions;

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

  public List<Writer> getWriters () {
    return writers;
  }

  public void setWriters (List<Writer> writers) {
    this.writers = writers;
  }

  public List<Edition> getEditions () {
    return editions;
  }

  public void setEditions (List<Edition> editions) {
    this.editions = editions;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((synopsis == null) ? 0 : synopsis.hashCode ());
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
    if (synopsis == null) {
      if (other.synopsis != null)
        return false;
    } else if (!synopsis.equals (other.synopsis)) {
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