package br.org.alexandria.websecurity.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.alexandria.websecurity.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

}