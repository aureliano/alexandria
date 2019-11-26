package br.org.alexandria.websecurity.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public @ResponseBody ResponseEntity<List<FunctionalityDTO>> functionalities () {
    final List<FunctionalityDTO> functionalities = this.functionalityService
        .findAllFunctionalitiesDTO ();
    return ResponseEntity.accepted ().body (functionalities);
  }

  @PostMapping(path = "/v1/functionalities", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<FunctionalityDTO> functionalitiesNew (
      @RequestBody FunctionalityDTO dto) {
    Long id = this.functionalityService.createFunctionality (dto);
    dto.setId (id);

    URI uri = this.webHelper.toURI ("/api/v1/functionalities/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }

  @PutMapping(path = "/v1/functionalities/{id}", consumes = "application/json")
  public @ResponseBody ResponseEntity<FunctionalityDTO> functionalitiesUpdate (
      @PathVariable Long id, @RequestBody FunctionalityDTO dto) {
    this.functionalityService.updateFunctionality (id, dto);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }

  @GetMapping(path = "/v1/functionalities/{id}", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<FunctionalityDTO> rolesId (
      @PathVariable Long id) {
    final FunctionalityDTO role = this.functionalityService
        .findFunctionalityDTO (id);
    return ResponseEntity.accepted ().body (role);
  }
}