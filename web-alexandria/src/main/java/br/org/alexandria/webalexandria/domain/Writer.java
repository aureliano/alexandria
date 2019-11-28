package br.org.alexandria.webalexandria.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "writers")
public class Writer {

  @Id
  @SequenceGenerator(name = "writers_seq_gen", sequenceName = "writers_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "writers_seq_gen")
  private Long id;

  @Column(name = "full_name", nullable = false, length = 50)
  private String fullName;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "writers")
  private List<Book> books;

  @Column(name = "nick_name", nullable = true, length = 25)
  private String nickName;

  @Column(name = "nationality", nullable = true, length = 50)
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

  public List<Book> getBooks () {
    return books;
  }

  public void setBooks (List<Book> books) {
    this.books = books;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode ());
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
    Writer other = (Writer) obj;
    if (fullName == null) {
      if (other.fullName != null)
        return false;
    } else if (!fullName.equals (other.fullName)) {
      return false;
    }
    return true;
  }
}