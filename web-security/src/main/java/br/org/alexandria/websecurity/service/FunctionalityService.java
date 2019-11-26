package br.org.alexandria.websecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.org.alexandria.websecurity.domain.Functionality;
import br.org.alexandria.websecurity.dto.FunctionalityDTO;
import br.org.alexandria.websecurity.exception.WebSecurityException;
import br.org.alexandria.websecurity.repository.FunctionalityRepository;

@Service
public class FunctionalityService {

  @Autowired
  private FunctionalityRepository functionalityRepository;

  public List<FunctionalityDTO> findAllFunctionalitiesDTO () {
    final List<FunctionalityDTO> functionalities = new ArrayList<> ();
    this.functionalityRepository.findAll ().forEach (f -> {
      FunctionalityDTO dto = new FunctionalityDTO ();
      dto.setId (f.getId ());
      dto.setFunctionality (f.getName ());

      functionalities.add (dto);
    });

    return functionalities;
  }

  public Long createFunctionality (FunctionalityDTO dto) {
    Functionality functionality = new Functionality ();
    functionality.setName (dto.getFunctionality ());
    functionality.setDescription (dto.getDescription ());

    this.functionalityRepository.save (functionality);
    return functionality.getId ();
  }

  public void updateFunctionality (Long id, FunctionalityDTO dto) {
    Optional<Functionality> optional = this.functionalityRepository
        .findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("Functionality not found.",
          HttpStatus.NOT_FOUND);
    }

    Functionality functionality = optional.get ();
    functionality.setName (dto.getFunctionality ());
    functionality.setDescription (dto.getDescription ());

    this.functionalityRepository.save (functionality);
  }

  public FunctionalityDTO findFunctionalityDTO (Long id) {
    Optional<Functionality> optional = this.functionalityRepository
        .findById (id);
    if (!optional.isPresent ()) {
      throw new WebSecurityException ("Functionality not found.",
          HttpStatus.NOT_FOUND);
    }

    Functionality functionality = optional.get ();
    FunctionalityDTO dto = new FunctionalityDTO ();
    dto.setId (functionality.getId ());
    dto.setFunctionality (functionality.getName ());
    dto.setDescription (functionality.getDescription ());

    return dto;
  }
}