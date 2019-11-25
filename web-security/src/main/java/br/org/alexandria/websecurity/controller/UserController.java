package br.org.alexandria.websecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.websecurity.dto.UserDTO;
import br.org.alexandria.websecurity.service.UserService;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(path = "/users", produces = { "application/json" })
  public @ResponseBody ResponseEntity<List<UserDTO>> users () {
    final List<UserDTO> users = this.userService.findAllUsersDTO ();
    return ResponseEntity.accepted ().body (users);
  }
}