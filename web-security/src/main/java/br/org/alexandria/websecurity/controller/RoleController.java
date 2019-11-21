package br.org.alexandria.websecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.repository.RoleRepository;

@Controller
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @GetMapping(path = "/roles", produces = { "application/json" })
  public @ResponseBody Iterable<Role> roles () {
    return this.roleRepository.findAll ();
  }
}