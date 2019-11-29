package br.org.alexandria.webalexandria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.alexandria.webalexandria.dto.WriterDTO;
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
}