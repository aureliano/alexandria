package br.org.alexandria.webalexandria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.org.alexandria.webalexandria.domain.Writer;
import br.org.alexandria.webalexandria.dto.WriterDTO;
import br.org.alexandria.webalexandria.exception.WebAlexandriaException;
import br.org.alexandria.webalexandria.repository.WriterRepository;

@Service
public class WriterService {

  @Autowired
  private WriterRepository writerRepository;

  public List<WriterDTO> findAllWritersDTO () {
    final List<WriterDTO> writers = new ArrayList<> ();
    this.writerRepository.findAll ().forEach (w -> {
      WriterDTO dto = new WriterDTO ();
      dto.setId (w.getId ());
      dto.setFullName (w.getFullName ());

      writers.add (dto);
    });

    return writers;
  }

  public Long createRole (WriterDTO dto) {
    Writer writer = new Writer ();
    writer.setFullName (dto.getFullName ());
    writer.setNickName (dto.getNickName ());
    writer.setNationality (dto.getNationality ());

    this.writerRepository.save (writer);
    return writer.getId ();
  }

  public void updateWriter (Long id, WriterDTO dto) {
    Optional<Writer> optional = this.writerRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Writer not found.",
          HttpStatus.NOT_FOUND);
    }

    Writer writer = optional.get ();
    writer.setFullName (dto.getFullName ());
    writer.setNickName (dto.getNickName ());
    writer.setNationality (dto.getNationality ());

    this.writerRepository.save (writer);
  }

  public WriterDTO findWriterDTO (Long id) {
    Optional<Writer> optional = this.writerRepository.findById (id);
    if (!optional.isPresent ()) {
      throw new WebAlexandriaException ("Writer not found.",
          HttpStatus.NOT_FOUND);
    }

    Writer writer = optional.get ();
    WriterDTO dto = new WriterDTO ();
    dto.setId (writer.getId ());
    dto.setFullName (writer.getFullName ());
    dto.setNickName (writer.getNickName ());
    dto.setNationality (writer.getNationality ());

    return dto;
  }
}