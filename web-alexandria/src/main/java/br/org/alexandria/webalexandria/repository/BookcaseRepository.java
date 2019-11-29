package br.org.alexandria.webalexandria.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.alexandria.webalexandria.domain.Bookcase;

public interface BookcaseRepository extends CrudRepository<Bookcase, Long> {

}