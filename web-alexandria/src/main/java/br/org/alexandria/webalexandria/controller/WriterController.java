package br.org.alexandria.webalexandria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.webalexandria.dto.WriterDTO;
import br.org.alexandria.webalexandria.service.WriterService;

@Controller
public class WriterController {

  @Autowired
  private WriterService writerService;

  @GetMapping(path = "/api/v1/writers", produces = { "application/json" })
  public @ResponseBody ResponseEntity<List<WriterDTO>> writers () {
    final List<WriterDTO> writers = this.writerService.findAllWritersDTO ();
    return ResponseEntity.accepted ().body (writers);
  }
}