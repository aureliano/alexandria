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

import br.org.alexandria.websecurity.dto.UserDTO;
import br.org.alexandria.websecurity.helper.WebHelper;
import br.org.alexandria.websecurity.service.UserService;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private WebHelper webHelper;

  @GetMapping(path = "/v1/users", produces = { "application/json" })
  public @ResponseBody ResponseEntity<List<UserDTO>> users () {
    final List<UserDTO> users = this.userService.findAllUsersDTO ();
    return ResponseEntity.accepted ().body (users);
  }

  @PostMapping(path = "/v1/users", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<UserDTO> rolesNew (
      @RequestBody UserDTO dto) {
    Long id = this.userService.registerUser (dto);
    dto.setId (id);
    dto.setPassword ("*****");
    dto.setConfirmPassword ("*****");

    URI uri = this.webHelper.toURI ("/api/v1/users/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }

  @PutMapping(path = "/api/v1/users/{id}", consumes = "application/json")
  public @ResponseBody ResponseEntity<UserDTO> rolesUpdate (
      @PathVariable Long id, @RequestBody UserDTO dto) {
    this.userService.updateUser (id, dto);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }
}