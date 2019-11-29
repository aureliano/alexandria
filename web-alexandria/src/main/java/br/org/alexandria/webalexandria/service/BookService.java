package br.org.alexandria.webalexandria.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.org.alexandria.commons.exception.AlexandriaCommonsException;
import br.org.alexandria.commons.helper.WebHelper;
import br.org.alexandria.webalexandria.domain.Book;
import br.org.alexandria.webalexandria.domain.Image;
import br.org.alexandria.webalexandria.domain.Writer;
import br.org.alexandria.webalexandria.dto.BookDTO;
import br.org.alexandria.webalexandria.dto.ImageDTO;
import br.org.alexandria.webalexandria.dto.PageDTO;
import br.org.alexandria.webalexandria.dto.WriterDTO;
import br.org.alexandria.webalexandria.exception.WebAlexandriaException;
import br.org.alexandria.webalexandria.repository.BookRepository;
import br.org.alexandria.webalexandria.repository.WriterRepository;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private WriterRepository writerRepository;

  @Autowired
  private WebHelper webHelper;

  public PageDTO<BookDTO> findAllBooksDTO (int pageNum, int pagesize) {
    try {
      this.webHelper.validatePageRequest (pageNum, pagesize);
    } catch (AlexandriaCommonsException e) {
      throw new WebAlexandriaException (e.getMessage (),
          HttpStatus.BAD_REQUEST);
    }

    Pageable pageable = PageRequest.of (--pageNum, pagesize);
    Page<Book> page = this.bookRepository.findAll (pageable);

    final List<BookDTO> books = new ArrayList<> ();
    page.getContent ().forEach (b -> {
      BookDTO dto = new BookDTO ();
      dto.setId (b.getId ());
      dto.setTitle (b.getTitle ());

      books.add (dto);
    });

    PageDTO<BookDTO> pageDTO = new PageDTO<> ();
    pageDTO.setData (books);
    pageDTO.setPageNumber (page.getNumber () + 1);
    pageDTO.setPageSize (page.getSize ());
    pageDTO.setTotalElements (page.getTotalElements ());
    pageDTO.setTotalPages (page.getTotalPages ());

    return pageDTO;
  }

  public Long createBook (BookDTO dto) {
    if (CollectionUtils.isEmpty (dto.getWriters ())) {
      throw new WebAlexandriaException ("Book must have at least one writer.",
          HttpStatus.BAD_REQUEST);
    }

    Book book = new Book ();
    book.setTitle (dto.getTitle ());
    book.setSynopsis (dto.getSynopsis ());
    book.setPublishingCompany (dto.getPublishingCompany ());
    book.setEdition (dto.getEdition ());
    book.setYear (dto.getYear ());
    book.setLanguage (dto.getLanguage ());
    book.setPages (dto.getPages ());
    book.setHeight (dto.getHeight ());
    book.setWidth (dto.getWidth ());
    book.setWeight (dto.getWeight ());
    book.setWriters (this.findWriters (dto));
    book.setImages (this.buildImages (dto.getImages ()));

    this.bookRepository.save (book);
    return book.getId ();
  }

  public void updateBook (Long id, BookDTO dto) {
    Optional<Book> optional = this.bookRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Book not found.",
          HttpStatus.NOT_FOUND);
    }

    if (CollectionUtils.isEmpty (dto.getWriters ())) {
      throw new WebAlexandriaException ("Book must have at least one writer.",
          HttpStatus.BAD_REQUEST);
    }

    Book book = optional.get ();
    book.setTitle (dto.getTitle ());
    book.setSynopsis (dto.getSynopsis ());
    book.setPublishingCompany (dto.getPublishingCompany ());
    book.setEdition (dto.getEdition ());
    book.setYear (dto.getYear ());
    book.setLanguage (dto.getLanguage ());
    book.setPages (dto.getPages ());
    book.setHeight (dto.getHeight ());
    book.setWidth (dto.getWidth ());
    book.setWeight (dto.getWeight ());
    book.setWriters (this.findWriters (dto));
    book.setImages (this.buildImages (dto.getImages ()));

    this.bookRepository.save (book);
  }

  public BookDTO findBookDTO (Long id) {
    Optional<Book> optional = this.bookRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Book not found.",
          HttpStatus.NOT_FOUND);
    }

    Book book = optional.get ();
    BookDTO dto = new BookDTO ();
    dto.setId (book.getId ());
    dto.setTitle (book.getTitle ());
    dto.setSynopsis (book.getSynopsis ());
    dto.setPublishingCompany (book.getPublishingCompany ());
    dto.setEdition (book.getEdition ());
    dto.setYear (book.getYear ());
    dto.setLanguage (book.getLanguage ());
    dto.setPages (book.getPages ());
    dto.setHeight (book.getHeight ());
    dto.setWidth (book.getWidth ());
    dto.setWeight (book.getWeight ());
    dto.setWriters (new ArrayList<> ());
    dto.setImages (new ArrayList<> ());

    book.getWriters ().forEach (e -> {
      WriterDTO w = new WriterDTO ();
      w.setId (e.getId ());
      w.setFullName (e.getFullName ());
      dto.getWriters ().add (w);
    });

    book.getImages ().forEach (e -> {
      ImageDTO i = new ImageDTO ();
      i.setId (e.getId ());
      dto.getImages ().add (i);
    });

    return dto;
  }

  private List<Image> buildImages (List<ImageDTO> images) {
    if (CollectionUtils.isEmpty (images)) {
      return Collections.emptyList ();
    }

    List<Image> source = new ArrayList<> (images.size ());
    images.forEach (i -> {
      Image e = new Image ();
      e.setFilePath (i.getFilePath ());

      source.add (e);
    });

    return source;
  }

  private List<Writer> findWriters (BookDTO dto) {
    List<Long> ids = new ArrayList<> (dto.getWriters ().size ());
    dto.getWriters ().forEach (w -> ids.add (w.getId ()));

    Iterable<Writer> iterableWriters = this.writerRepository.findAllById (ids);
    List<Writer> writers = new ArrayList<> ();
    iterableWriters.forEach (w -> writers.add (w));

    if (writers.size () < dto.getWriters ().size ()) {
      throw new WebAlexandriaException ("Not all writers could be found.",
          HttpStatus.BAD_REQUEST);
    }

    return writers;
  }
}