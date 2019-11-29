package br.org.alexandria.webalexandria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.org.alexandria.commons.helper.WebHelper;
import br.org.alexandria.webalexandria.service.BookService;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;

  @Autowired
  private WebHelper webHelper;
}