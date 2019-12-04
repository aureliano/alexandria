package br.org.alexandria.webalexandria.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import br.org.alexandria.commons.helper.FileHelper;
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

  @Autowired
  private FileHelper fileHelper;

  @Value("${web-alexandria.storage.images}")
  private String storageImagesPath;

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
    edition.getImages ().forEach (i -> {
      i.setEdition (null);
      i.setTemporary (true);
    });
    this.imageRepository.saveAll (edition.getImages ());
    edition.getImages ().clear ();

    this.editionRepository.delete (edition);
    logger.info ("Deleted {} edition.", id);
  }

  public List<ImageDTO> uploadImages (Long editionId, MultipartFile[] files) {
    if ((files == null) || (files.length < 1)) {
      throw new WebAlexandriaException ("No file uploaded.",
          HttpStatus.BAD_REQUEST);
    }

    Optional<Edition> optional = this.editionRepository.findById (editionId);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Edition not found.",
          HttpStatus.NOT_FOUND);
    }

    Edition edition = optional.get ();
    List<Image> images = this.saveTemporaryImages (files);
    int index = 0;

    for (Image image : images) {
      Path path = this.resolveImagePath (image.getId ());
      MultipartFile file = files[index++];

      try {
        IOUtils.copy (file.getInputStream (),
            new FileOutputStream (path.toFile ()));
      } catch (IOException e) {
        throw new WebAlexandriaException (e);
      }

      image.setContentType (file.getContentType ());
      image.setEdition (edition);
      image.setTemporary (false);
    }

    this.imageRepository.saveAll (images);

    return this.imageToImageDTO (images);
  }

  private List<Image> saveTemporaryImages (MultipartFile[] files) {
    List<Image> images = new ArrayList<> (files.length);

    for (int i = 0; i < files.length; i++) {
      Image image = new Image ();
      image.setTemporary (true);
      images.add (image);
    }

    List<Image> entities = new ArrayList<> (files.length);
    this.imageRepository.saveAll (images).forEach (i -> {
      entities.add (i);
    });

    return entities;
  }

  private Path resolveImagePath (Long id) {
    Path subDir = this.fileHelper.resolveFileSubDirectory (id.toString ());
    Path dir = Paths.get (this.storageImagesPath, subDir.toString ());

    if (!dir.toFile ().mkdirs ()) {
      logger.error ("Could not create directory {}", dir.toString ());
      throw new WebAlexandriaException ("Unable to create directory.");
    }

    return Paths.get (this.storageImagesPath, subDir.toString (),
        id.toString ());
  }

  private List<ImageDTO> imageToImageDTO (List<Image> images) {
    List<ImageDTO> imagesDTO = new ArrayList<> (images.size ());
    images.forEach (i -> {
      ImageDTO dto = new ImageDTO ();
      dto.setId (i.getId ());
      imagesDTO.add (dto);
    });

    return imagesDTO;
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
}