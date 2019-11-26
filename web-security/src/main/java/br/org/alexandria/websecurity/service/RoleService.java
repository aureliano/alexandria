package br.org.alexandria.websecurity.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.dto.RoleDTO;
import br.org.alexandria.websecurity.repository.RoleRepository;

@Service
public class RoleService {

  private static final String COUNT_ROLE_REFERENCES = "SELECT count(*) FROM user_roles_users WHERE role_id = ?";

  @PersistenceContext
  private EntityManager em;

  @Autowired
  private RoleRepository roleRepository;

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
    Role role = new Role ();
    role.setName (dto.getRole ());
    role.setDescription (dto.getDescription ());

    this.roleRepository.save (role);
    return role.getId ();
  }
}