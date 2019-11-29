package br.org.alexandria.webalexandria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.org.alexandria.webalexandria.domain.Book;
import br.org.alexandria.webalexandria.dto.BookDTO;
import br.org.alexandria.webalexandria.dto.PageDTO;
import br.org.alexandria.webalexandria.exception.WebAlexandriaException;
import br.org.alexandria.webalexandria.repository.BookRepository;

@Service
public class BookService {

  private static final Integer MAX_PAGE_SIZE = 20;

  @Autowired
  private BookRepository bookRepository;

  public PageDTO<BookDTO> findAllBooksDTO (int pageNum, int pagesize) {
    if (pagesize > MAX_PAGE_SIZE) {
      String msg = String.format ("Page size must be at most %d.",
          MAX_PAGE_SIZE);
      throw new WebAlexandriaException (msg, HttpStatus.BAD_REQUEST);
    }

    Pageable pageable = PageRequest.of (pageNum, pagesize);
    Page<Book> page = this.bookRepository.findAll (pageable);

    final List<BookDTO> books = new ArrayList<> (page.getNumberOfElements ());
    page.getContent ().forEach (b -> {
      BookDTO dto = new BookDTO ();
      dto.setId (b.getId ());
      dto.setTitle (b.getTitle ());

      books.add (dto);
    });

    PageDTO<BookDTO> pageDTO = new PageDTO<> ();
    pageDTO.setData (books);
    pageDTO.setPageNumber (page.getNumber ());
    pageDTO.setPageSize (page.getSize ());
    pageDTO.setTotalElements (page.getTotalElements ());
    pageDTO.setTotalPages (page.getTotalPages ());

    return pageDTO;
  }
}