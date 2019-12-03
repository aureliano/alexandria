package br.org.alexandria.webalexandria.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.commons.helper.WebHelper;
import br.org.alexandria.webalexandria.dto.BookDTO;
import br.org.alexandria.webalexandria.dto.PageDTO;
import br.org.alexandria.webalexandria.service.BookService;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private WebHelper webHelper;

  @GetMapping(path = "/api/v1/books", params = { "page", "size" }, produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<PageDTO<BookDTO>> books (
      @RequestParam int page, @RequestParam int size) {
    final PageDTO<BookDTO> books = this.bookService.findAllBooksDTO (page,
        size);
    return ResponseEntity.accepted ().body (books);
  }

  @PostMapping(path = "/api/v1/books", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<BookDTO> booksNew (
      @RequestBody BookDTO dto) {
    Long id = this.bookService.createBook (dto);
    dto.setId (id);

    URI uri = this.webHelper.toURI ("/api/v1/books/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }

  @PutMapping(path = "/api/v1/books/{id}", consumes = "application/json")
  public @ResponseBody ResponseEntity<BookDTO> booksUpdate (
      @PathVariable Long id, @RequestBody BookDTO dto) {
    this.bookService.updateBook (id, dto);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }

  @GetMapping(path = "/api/v1/books/{id}", produces = { "application/json" })
  public @ResponseBody ResponseEntity<BookDTO> booksId (@PathVariable Long id) {
    final BookDTO book = this.bookService.findBookDTO (id);
    return ResponseEntity.accepted ().body (book);
  }

  @DeleteMapping(path = "/api/v1/books/{id}", produces = { "application/json" })
  public @ResponseBody ResponseEntity<BookDTO> booksDelete (
      @PathVariable Long id) {
    this.bookService.deleteBook (id);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }
}