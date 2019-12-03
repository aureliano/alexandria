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
@Table(name = "images")
public class Image {

  @Id
  @SequenceGenerator(name = "images_seq_gen", sequenceName = "images_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_seq_gen")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "edition_id", nullable = false)
  private Edition edition;

  @Column(name = "temporary", nullable = false)
  private Boolean temporary;

  @Column(name = "file_path", nullable = false, length = 300)
  private String filePath;

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

  public Boolean getTemporary () {
    return temporary;
  }

  public void setTemporary (Boolean temporary) {
    this.temporary = temporary;
  }

  public String getFilePath () {
    return filePath;
  }

  public void setFilePath (String filePath) {
    this.filePath = filePath;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((filePath == null) ? 0 : filePath.hashCode ());
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
    Image other = (Image) obj;
    if (filePath == null) {
      if (other.filePath != null)
        return false;
    } else if (!filePath.equals (other.filePath)) {
      return false;
    }
    return true;
  }
}