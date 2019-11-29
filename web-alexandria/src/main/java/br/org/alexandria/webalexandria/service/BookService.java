package br.org.alexandria.webalexandria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.alexandria.webalexandria.repository.BookRepository;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;
}