package br.org.alexandria.websecurity.controller;

import java.net.URI;
import java.util.List;
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

  @GetMapping(path = "/v1/roles", produces = { "application/json" })
  public @ResponseBody ResponseEntity<List<RoleDTO>> roles () {
    final List<RoleDTO> roles = this.roleService.findAllRolesDTO ();
    return ResponseEntity.accepted ().body (roles);
  }

  @PostMapping(path = "/v1/roles", consumes = "application/json", produces = {
      "application/json" })
  public @ResponseBody ResponseEntity<RoleDTO> rolesNew (
      @RequestBody RoleDTO dto) {
    Long id = this.roleService.createRole (dto);
    dto.setId (id);

    URI uri = this.webHelper.toURI ("/api/v1/roles/" + dto.getId ());
    return ResponseEntity.created (uri).body (dto);
  }

  @PutMapping(path = "/v1/roles/{id}", consumes = "application/json")
  public @ResponseBody ResponseEntity<RoleDTO> rolesUpdate (
      @PathVariable Long id, @RequestBody RoleDTO dto) {
    this.roleService.updateRole (id, dto);
    return ResponseEntity.status (HttpStatus.NO_CONTENT).build ();
  }

  @GetMapping(path = "/v1/roles/{id}", produces = { "application/json" })
  public @ResponseBody ResponseEntity<RoleDTO> rolesId (@PathVariable Long id) {
    final RoleDTO role = this.roleService.findRoleDTO (id);
    return ResponseEntity.accepted ().body (role);
  }

  @DeleteMapping(path = "/v1/roles/{id}", produces = { "application/json" })
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