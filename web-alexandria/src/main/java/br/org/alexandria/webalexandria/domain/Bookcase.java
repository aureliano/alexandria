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
  @JoinColumn(name = "edition_id", nullable = false)
  private Edition edition;

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

  public Edition getEdition () {
    return edition;
  }

  public void setEdition (Edition edition) {
    this.edition = edition;
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
    result = prime * result + ((edition == null) ? 0 : edition.hashCode ());
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
    if (edition == null) {
      if (other.edition != null)
        return false;
    } else if (!edition.equals (other.edition)) {
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