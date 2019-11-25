package br.org.alexandria.websecurity.service;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

@Service
public class RoleService {

  private static final String COUNT_ROLE_REFERENCES = "SELECT count(*) FROM user_roles_users WHERE role_id = ?";

  @PersistenceContext
  private EntityManager em;

  public Integer countUserRoles (Long roleId) {
    Query query = em.createNativeQuery (COUNT_ROLE_REFERENCES);
    query.setParameter (1, roleId);

    return ((BigInteger) query.getSingleResult ()).intValue ();
  }
}