package br.org.alexandria.websecurity.domain;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class Role {

  @Id
  @SequenceGenerator(name = "user_roles_seq_gen", sequenceName = "user_roles_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_seq_gen")
  private long id;

  @Column(name = "name", nullable = false, length = 25, unique = true)
  private String name;

  @Column(name = "description", nullable = false, length = 100)
  private String description;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
  private List<User> users;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "roles_functionalities", joinColumns = {
      @JoinColumn(name = "role_id") }, inverseJoinColumns = {
          @JoinColumn(name = "functionality_id") })
  private List<Functionality> functionalities;

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

  public List<User> getUsers () {
    return users;
  }

  public void setUsers (List<User> users) {
    this.users = users;
  }

  public List<Functionality> getFunctionalities () {
    return functionalities;
  }

  public void setFunctionalities (List<Functionality> functionalities) {
    this.functionalities = functionalities;
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
    Role other = (Role) obj;
    if (this.description == null) {
      if (other.description != null)
        return false;
    } else if (!this.description.equals (other.description)) {
      return false;
    }
    if (this.id != other.id)
      return false;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals (other.name)) {
      return false;
    }
    return true;
  }
}