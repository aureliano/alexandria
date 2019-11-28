package br.org.alexandria.websecurity.domain;

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
@Table(name = "functionalities")
public class Functionality {

  @Id
  @SequenceGenerator(name = "functionalities_seq_gen", sequenceName = "functionalities_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "functionalities_seq_gen")
  private long id;

  @Column(name = "name", nullable = false, length = 25, unique = true)
  private String name;

  @Column(name = "description", nullable = false, length = 100)
  private String description;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "functionalities")
  private List<Role> roles;

  public long getId () {
    return id;
  }

  public void setId (long id) {
    this.id = id;
  }

  public String getName () {
    return name;
  }

  public void setName (String name) {
    this.name = name;
  }

  public String getDescription () {
    return description;
  }

  public void setDescription (String description) {
    this.description = description;
  }

  public List<Role> getRoles () {
    return roles;
  }

  public void setRoles (List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public int hashCode () {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((description == null) ? 0 : description.hashCode ());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode ());
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
    Functionality other = (Functionality) obj;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals (other.description)) {
      return false;
    }
    if (id != other.id)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals (other.name)) {
      return false;
    }
    return true;
  }
}