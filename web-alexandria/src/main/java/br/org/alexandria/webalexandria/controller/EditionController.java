package br.org.alexandria.webalexandria.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.commons.helper.WebHelper;
import br.org.alexandria.webalexandria.dto.EditionDTO;
import br.org.alexandria.webalexandria.service.EditionService;

@Controller
public class EditionController {

  @Autowired
  private EditionService editionService;

  @Autowired
  private WebHelper webHelper;

  @PostMapping(path = "/api/v1/editions", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<EditionDTO> editionsNew (
      @RequestBody EditionDTO dto) {
    Long id = this.editionService.createEdition (dto);
    dto.setId (id);

    URI uri = this.webHelper.toURI ("/api/v1/editions/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }

  @PutMapping(path = "/api/v1/editions/{id}", consumes = "application/json")
  public @ResponseBody ResponseEntity<EditionDTO> editionsUpdate (
      @PathVariable Long id, @RequestBody EditionDTO dto) {
    this.editionService.updateEdition (id, dto);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }

  @DeleteMapping(path = "/api/v1/editions/{id}", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<EditionDTO> editionsDelete (
      @PathVariable Long id) {
    this.editionService.deleteEdition (id);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }
}