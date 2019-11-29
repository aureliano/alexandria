package br.org.alexandria.webalexandria.repository;

import org.springframework.data.repository.CrudRepository;

import br.org.alexandria.webalexandria.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}