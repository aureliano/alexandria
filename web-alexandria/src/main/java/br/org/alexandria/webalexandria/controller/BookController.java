package br.org.alexandria.webalexandria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
  public @ResponseBody ResponseEntity<PageDTO<BookDTO>> users (
      @RequestParam int page, @RequestParam int size) {
    final PageDTO<BookDTO> books = this.bookService.findAllBooksDTO (page,
        size);
    return ResponseEntity.accepted ().body (books);
  }
}