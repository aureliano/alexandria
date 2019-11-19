package br.org.alexandria.websecurity.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.alexandria.websecurity.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}