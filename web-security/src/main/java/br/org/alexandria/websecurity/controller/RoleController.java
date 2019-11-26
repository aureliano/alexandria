package br.org.alexandria.websecurity.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.org.alexandria.websecurity.domain.Role;
import br.org.alexandria.websecurity.dto.RoleDTO;
import br.org.alexandria.websecurity.helper.WebHelper;
import br.org.alexandria.websecurity.repository.RoleRepository;
import br.org.alexandria.websecurity.service.RoleService;

@Controller
public class RoleController {

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private RoleService roleService;

  @Autowired
  private WebHelper webHelper;

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

    URI uri = this.webHelper.toURI ("/roles/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }

  @PutMapping(path = "/roles/{id}", consumes = "application/json")
  public @ResponseBody ResponseEntity<RoleDTO> rolesUpdate (
      @PathVariable Long id, @RequestBody RoleDTO dto) {
    Optional<Role> optional = this.roleRepository.findById (id);
    if (!optional.isPresent ()) {
      return ResponseEntity.notFound ().build ();
    }

    Role role = optional.get ();
    role.setName (dto.getRole ());
    role.setDescription (dto.getDescription ());

    this.roleRepository.save (role);
    dto.setId (role.getId ());

    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }

  @DeleteMapping(path = "/roles/{id}", produces = { "application/json" })
  public @ResponseBody ResponseEntity<RoleDTO> rolesDelete (
      @PathVariable Long id) {
    Optional<Role> optional = this.roleRepository.findById (id);
    if (!optional.isPresent ()) {
      return ResponseEntity.notFound ().build ();
    }

    Role role = optional.get ();
    if (this.roleService.countUserRoles (id) > 0) {
      String msg = "You cannot delete a role which still has users related to it.";
      throw new DataIntegrityViolationException (msg);
    }

    this.roleRepository.delete (role);

    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }
}