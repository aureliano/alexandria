package br.org.alexandria.webalexandria.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.alexandria.webalexandria.domain.Writer;

public interface WriterRepository extends CrudRepository<Writer, Long> {

}