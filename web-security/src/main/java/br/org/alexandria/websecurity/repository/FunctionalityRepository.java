package br.org.alexandria.websecurity.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.alexandria.websecurity.domain.Functionality;

public interface FunctionalityRepository
    extends CrudRepository<Functionality, Long> {

}