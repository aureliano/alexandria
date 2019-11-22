package br.org.alexandria.websecurity.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.dto.RoleDTO;
import br.org.alexandria.websecurity.helper.WebHelper;
import br.org.alexandria.websecurity.repository.RoleRepository;

@Controller
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @GetMapping(path = "/roles", produces = { "application/json" })
  public @ResponseBody Iterable<Role> roles () {
    return this.roleRepository.findAll ();
  }

  @PostMapping(path = "/roles", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<RoleDTO> rolesNew (
      @RequestBody RoleDTO dto) {
    Role role = new Role ();
    role.setName (dto.getRole ());
    role.setDescription (dto.getDescription ());

    this.roleRepository.save (role);
    dto.setId (role.getId ());

    URI uri = WebHelper.toURI ("/roles/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }
}