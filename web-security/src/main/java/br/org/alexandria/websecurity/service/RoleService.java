package br.org.alexandria.websecurity.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.org.alexandria.websecurity.domain.Functionality;
import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.dto.RoleDTO;
import br.org.alexandria.websecurity.exception.WebSecurityException;
import br.org.alexandria.websecurity.repository.FunctionalityRepository;
import br.org.alexandria.websecurity.repository.RoleRepository;

@Service
public class RoleService {

  private static final String COUNT_ROLE_REFERENCES = "SELECT count(*) FROM user_roles_users WHERE role_id = ?";

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private FunctionalityRepository functionalityRepository;

  public Integer countUserRoles (Long roleId) {
    Query query = em.createNativeQuery (COUNT_ROLE_REFERENCES);
    query.setParameter (1, roleId);

    return ((BigInteger) query.getSingleResult ()).intValue ();
  }

  public List<RoleDTO> findAllRolesDTO () {
    final List<RoleDTO> roles = new ArrayList<> ();
    this.roleRepository.findAll ().forEach (r -> {
      RoleDTO dto = new RoleDTO ();
      dto.setId (r.getId ());
      dto.setRole (r.getName ());

      roles.add (dto);
    });

    return roles;
  }

  public Long createRole (RoleDTO dto) {
    if (CollectionUtils.isEmpty (dto.getFunctionalities ())) {
      throw new WebSecurityException (
          "Role must have at least one functionality.", HttpStatus.BAD_REQUEST);
    }

    Role role = new Role ();
    role.setName (dto.getRole ());
    role.setDescription (dto.getDescription ());
    role.setFunctionalities (this.findFunctionalities (dto));

    this.roleRepository.save (role);
    return role.getId ();
  }

  public void updateRole (Long id, RoleDTO dto) {
    Optional<Role> optional = this.roleRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("Role not found.", HttpStatus.NOT_FOUND);
    }

    if (CollectionUtils.isEmpty (dto.getFunctionalities ())) {
      throw new WebSecurityException (
          "Role must have at least one functionality.", HttpStatus.BAD_REQUEST);
    }

    Role role = optional.get ();
    role.setName (dto.getRole ());
    role.setDescription (dto.getDescription ());
    role.setFunctionalities (this.findFunctionalities (dto));

    this.roleRepository.save (role);
  }

  public RoleDTO findRoleDTO (Long id) {
    Optional<Role> optional = this.roleRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("Role not found.", HttpStatus.NOT_FOUND);
    }

    Role role = optional.get ();
    RoleDTO dto = new RoleDTO ();
    dto.setId (role.getId ());
    dto.setRole (role.getName ());
    dto.setDescription (role.getDescription ());

    return dto;
  }

  public void deleteRole (Long id) {
    Optional<Role> optional = this.roleRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("Role not found.", HttpStatus.NOT_FOUND);
    }

    Role role = optional.get ();
    role.getFunctionalities ().clear ();
    this.roleRepository.delete (role);
  }

  private List<Functionality> findFunctionalities (RoleDTO dto) {
    List<Long> ids = new ArrayList<> (dto.getFunctionalities ().size ());
    dto.getFunctionalities ().forEach (f -> ids.add (f.getId ()));

    Iterable<Functionality> iterableFunctionalities = this.functionalityRepository
        .findAllById (ids);
    List<Functionality> functionalities = new ArrayList<> ();
    iterableFunctionalities.forEach (f -> functionalities.add (f));

    if (functionalities.size () < dto.getFunctionalities ().size ()) {
      throw new WebSecurityException ("Not all functionalities could be found.",
          HttpStatus.BAD_REQUEST);
    }

    return functionalities;
  }
}