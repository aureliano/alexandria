package br.org.alexandria.webalexandria.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "bookcase")
public class Bookcase {

  @Id
  @SequenceGenerator(name = "bookcase_seq_gen", sequenceName = "bookcase_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookcase_seq_gen")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @Column(name = "comments", nullable = true, length = 300)
  private String comments;

  @Column(name = "read", nullable = false)
  private Boolean read;

  @Column(name = "gender", nullable = true, length = 25)
  private String gender;

  public Long getId () {
    return id;
  }

  public void setId (Long id) {
    this.id = id;
  }

  public Book getBook () {
    return book;
  }

  public void setBook (Book book) {
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

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((book == null) ? 0 : book.hashCode ());
    result = prime * result + ((read == null) ? 0 : read.hashCode ());
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
    Bookcase other = (Bookcase) obj;
    if (book == null) {
      if (other.book != null)
        return false;
    } else if (!book.equals (other.book)) {
      return false;
    }
    if (read == null) {
      if (other.read != null)
        return false;
    } else if (!read.equals (other.read)) {
      return false;
    }
    return true;
  }
}