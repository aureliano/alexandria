package br.org.alexandria.webalexandria.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.org.alexandria.webalexandria.domain.Image;

public interface ImageRepository
    extends PagingAndSortingRepository<Image, Long> {

}