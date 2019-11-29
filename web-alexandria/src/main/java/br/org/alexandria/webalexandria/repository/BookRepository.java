package br.org.alexandria.webalexandria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.org.alexandria.webalexandria.domain.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

}