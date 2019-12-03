package br.org.alexandria.webalexandria.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.org.alexandria.webalexandria.domain.Book;
import br.org.alexandria.webalexandria.domain.Edition;
import br.org.alexandria.webalexandria.domain.Image;
import br.org.alexandria.webalexandria.dto.EditionDTO;
import br.org.alexandria.webalexandria.dto.ImageDTO;
import br.org.alexandria.webalexandria.exception.WebAlexandriaException;
import br.org.alexandria.webalexandria.repository.BookRepository;
import br.org.alexandria.webalexandria.repository.EditionRepository;
import br.org.alexandria.webalexandria.repository.ImageRepository;

@Service
public class EditionService {

  private static final Logger logger = LoggerFactory
      .getLogger (EditionService.class);

  @Autowired
  private EditionRepository editionRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private ImageRepository imageRepository;

  public Long createEdition (EditionDTO dto) {
    if (dto.getBookId () == null) {
      throw new WebAlexandriaException ("Book is required.",
          HttpStatus.BAD_REQUEST);
    }

    Optional<Book> optional = this.bookRepository.findById (dto.getBookId ());
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Book not found.",
          HttpStatus.NOT_FOUND);
    }

    Edition edition = new Edition ();
    edition.setPublishingCompany (dto.getPublishingCompany ());
    edition.setEdition (dto.getEdition ());
    edition.setYear (dto.getYear ());
    edition.setLanguage (dto.getLanguage ());
    edition.setPages (dto.getPages ());
    edition.setHeight (dto.getHeight ());
    edition.setWidth (dto.getWidth ());
    edition.setWeight (dto.getWeight ());
    edition.setBook (optional.get ());
    edition.setImages (this.buildImages (dto.getImages ()));

    this.editionRepository.save (edition);
    logger.info ("Edition {} created.", edition.getId ());
    return edition.getId ();
  }

  public void updateEdition (Long id, EditionDTO dto) {
    Optional<Edition> optional = this.editionRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Edition not found.",
          HttpStatus.NOT_FOUND);
    }

    Optional<Book> option = this.bookRepository.findById (dto.getBookId ());
    if (!option.isPresent ()) {
      throw new WebAlexandriaException ("Book not found.",
          HttpStatus.NOT_FOUND);
    }

    Edition edition = optional.get ();
    edition.setPublishingCompany (dto.getPublishingCompany ());
    edition.setEdition (dto.getEdition ());
    edition.setYear (dto.getYear ());
    edition.setLanguage (dto.getLanguage ());
    edition.setPages (dto.getPages ());
    edition.setHeight (dto.getHeight ());
    edition.setWidth (dto.getWidth ());
    edition.setWeight (dto.getWeight ());
    edition.setBook (option.get ());
    edition.setImages (this.buildImages (dto.getImages ()));

    this.editionRepository.save (edition);
    logger.info ("Edition {} updated.", id);
  }

  public void deleteEdition (Long id) {
    Optional<Edition> optional = this.editionRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Edition not found.",
          HttpStatus.NOT_FOUND);
    }

    Edition edition = optional.get ();
    this.deleteFilesFromStorage (edition);
    edition.getImages ().clear ();

    this.editionRepository.delete (edition);
    logger.info ("Deleted {} edition.", id);
  }

  private List<Image> buildImages (List<ImageDTO> images) {
    if (CollectionUtils.isEmpty (images)) {
      return Collections.emptyList ();
    }

    List<Long> ids = new ArrayList<> (images.size ());
    images.forEach (i -> {
      ids.add (i.getId ());
    });

    Iterable<Image> iterable = this.imageRepository.findAllById (ids);
    List<Image> entities = new ArrayList<> (images.size ());
    iterable.forEach (i -> {
      entities.add (i);
    });

    return entities;
  }

  private void deleteFilesFromStorage (Edition edition) {
    edition.getImages ().forEach (i -> {
      Path path = Paths.get (i.getFilePath ());
      try {
        Files.delete (path);
      } catch (IOException e) {
        logger.error (e.getMessage (), e);
      }
    });
  }
}