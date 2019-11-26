package br.org.alexandria.websecurity.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.domain.User;
import br.org.alexandria.websecurity.dto.RoleDTO;
import br.org.alexandria.websecurity.dto.UserDTO;
import br.org.alexandria.websecurity.exception.WebSecurityException;
import br.org.alexandria.websecurity.helper.SecurityHelper;
import br.org.alexandria.websecurity.repository.RoleRepository;
import br.org.alexandria.websecurity.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private SecurityHelper securityHelper;

  public List<UserDTO> findAllUsersDTO () {
    final List<UserDTO> users = new ArrayList<> ();
    this.userRepository.findAll ().forEach (u -> {
      UserDTO dto = new UserDTO ();
      dto.setId (u.getId ());
      dto.setName (u.getName ());

      final Set<RoleDTO> roles = new HashSet<> ();
      u.getRoles ().forEach (e -> {
        RoleDTO r = new RoleDTO ();
        r.setId (e.getId ());
        r.setRole (e.getName ());
        roles.add (r);
      });

      dto.setRoles (roles);
      users.add (dto);
    });

    return users;
  }

  public Long registerUser (UserDTO dto) {
    if (dto.getPassword () != null
        && !dto.getPassword ().equals (dto.getConfirmPassword ())) {
      throw new WebSecurityException ("Password != confirmedPassword",
          HttpStatus.BAD_REQUEST);
    }

    if (CollectionUtils.isEmpty (dto.getRoles ())) {
      throw new WebSecurityException ("User must have at least one role.",
          HttpStatus.BAD_REQUEST);
    }

    User user = new User ();
    user.setName (dto.getName ());
    user.setPassword (this.securityHelper.encodePassword (dto.getPassword ()));
    user.setRoles (this.findRoles (dto));

    this.userRepository.save (user);
    return user.getId ();
  }

  public void updateUser (Long id, UserDTO dto) {
    Optional<User> optional = this.userRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("User not found.", HttpStatus.NOT_FOUND);
    }

    if (CollectionUtils.isEmpty (dto.getRoles ())) {
      throw new WebSecurityException ("User must have at least one role.",
          HttpStatus.BAD_REQUEST);
    }

    User user = optional.get ();
    user.setName (dto.getName ());
    user.setRoles (this.findRoles (dto));

    this.userRepository.save (user);
  }

  public void changePassword (Long id, UserDTO dto) {
    if (dto.getPassword () != null
        && !dto.getPassword ().equals (dto.getConfirmPassword ())) {
      throw new WebSecurityException ("Password != confirmedPassword",
          HttpStatus.BAD_REQUEST);
    }

    Optional<User> optional = this.userRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("User not found.", HttpStatus.NOT_FOUND);
    }

    User user = optional.get ();
    user.setPassword (this.securityHelper.encodePassword (dto.getPassword ()));

    this.userRepository.save (user);
  }

  private List<Role> findRoles (UserDTO dto) {
    List<Long> ids = new ArrayList<> (dto.getRoles ().size ());
    dto.getRoles ().forEach (r -> ids.add (r.getId ()));

    Iterable<Role> iterableRoles = this.roleRepository.findAllById (ids);
    List<Role> roles = new ArrayList<> ();
    iterableRoles.forEach (r -> roles.add (r));

    if (roles.size () < dto.getRoles ().size ()) {
      throw new WebSecurityException ("Not all roles could be found.",
          HttpStatus.BAD_REQUEST);
    }

    return roles;
  }
}