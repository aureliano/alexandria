package br.org.alexandria.webalexandria.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.commons.helper.WebHelper;
import br.org.alexandria.webalexandria.dto.WriterDTO;
import br.org.alexandria.webalexandria.service.WriterService;

@Controller
public class WriterController {

  @Autowired
  private WriterService writerService;

  @Autowired
  private WebHelper webHelper;

  @GetMapping(path = "/api/v1/writers", produces = { "application/json" })
  public @ResponseBody ResponseEntity<List<WriterDTO>> writers () {
    final List<WriterDTO> writers = this.writerService.findAllWritersDTO ();
    return ResponseEntity.accepted ().body (writers);
  }

  @PostMapping(path = "/api/v1/writers", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<WriterDTO> writersNew (
      @RequestBody WriterDTO dto) {
    Long id = this.writerService.createRole (dto);
    dto.setId (id);

    URI uri = this.webHelper.toURI ("/api/v1/writers/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }
}