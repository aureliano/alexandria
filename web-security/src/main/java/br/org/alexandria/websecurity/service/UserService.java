package br.org.alexandria.websecurity.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.domain.User;
import br.org.alexandria.websecurity.dto.UserDTO;
import br.org.alexandria.websecurity.exception.WebSecurityException;
import br.org.alexandria.websecurity.helper.SecurityHelper;
import br.org.alexandria.websecurity.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> findAllUsersDTO () {
    final List<UserDTO> users = new ArrayList<UserDTO> ();
    this.userRepository.findAll ().forEach (new Consumer<User> () {
      public void accept (User u) {
        UserDTO dto = new UserDTO ();
        dto.setId (u.getId ());
        dto.setName (u.getName ());

        final Set<String> roles = new HashSet<String> ();
        u.getRoles ().forEach (new Consumer<Role> () {
          public void accept (Role e) {
            roles.add (e.getName ());
          }
        });

        dto.setRoles (roles);
        users.add (dto);
      }
    });

    return users;
  }

  public Long registerUser (UserDTO dto) {
    if (dto.getPassword () != null
        && !dto.getPassword ().equals (dto.getConfirmPassword ())) {
      throw new WebSecurityException ("Password != confirmedPassword",
          HttpStatus.BAD_REQUEST);
    }

    User user = new User ();
    user.setName (dto.getName ());
    user.setPassword (SecurityHelper.encodePassword (dto.getPassword ()));

    this.userRepository.save (user);
    return user.getId ();
  }

  public void updateUser (Long id, UserDTO dto) {
    Optional<User> optional = this.userRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("User not found.", HttpStatus.NOT_FOUND);
    }

    User user = new User ();
    user.setName (dto.getName ());

    this.userRepository.save (user);
  }
}