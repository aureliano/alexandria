package br.org.alexandria.websecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.alexandria.websecurity.dto.FunctionalityDTO;
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
}