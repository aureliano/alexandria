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
@Table(name = "users")
public class User {

  @Id
  @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_seq", initialValue = 1, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
  private long id;

  @Column(name = "name", nullable = false, length = 25)
  private String name;

  @Column(name = "password", nullable = false, length = 25)
  private String password;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "user_roles_users", joinColumns = {
      @JoinColumn(name = "user_id") }, inverseJoinColumns = {
          @JoinColumn(name = "role_id") })
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

  public String getPassword () {
    return password;
  }

  public void setPassword (String password) {
    this.password = password;
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
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + ((name == null) ? 0 : name.hashCode ());
    result = prime * result + ((password == null) ? 0 : password.hashCode ());
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
    User other = (User) obj;
    if (this.id != other.id)
      return false;
    if (this.name == null) {
      if (other.name != null)
        return false;
    } else if (!this.name.equals (other.name)) {
      return false;
    }
    if (this.password == null) {
      if (other.password != null)
        return false;
    } else if (!this.password.equals (other.password)) {
      return false;
    }
    return true;
  }
}