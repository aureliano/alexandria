package br.org.alexandria.websecurity.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.websecurity.dto.FunctionalityDTO;
import br.org.alexandria.websecurity.helper.WebHelper;
import br.org.alexandria.websecurity.service.FunctionalityService;

@Controller
public class FunctionalityController {

  @Autowired
  private FunctionalityService functionalityService;

  @Autowired
  private WebHelper webHelper;

  @GetMapping(path = "/v1/functionalities", produces = { "application/json" })
  public @ResponseBody ResponseEntity<List<FunctionalityDTO>> roles () {
    final List<FunctionalityDTO> functionalities = this.functionalityService
        .findAllFunctionalitiesDTO ();
    return ResponseEntity.accepted ().body (functionalities);
  }

  @PostMapping(path = "/v1/functionalities", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<FunctionalityDTO> rolesNew (
      @RequestBody FunctionalityDTO dto) {
    Long id = this.functionalityService.createFunctionality (dto);
    dto.setId (id);

    URI uri = this.webHelper.toURI ("/api/v1/functionalities/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }
}