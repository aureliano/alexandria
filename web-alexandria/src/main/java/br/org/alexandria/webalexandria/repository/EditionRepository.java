package br.org.alexandria.webalexandria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.org.alexandria.webalexandria.domain.Edition;

public interface EditionRepository
    extends PagingAndSortingRepository<Edition, Long> {

}