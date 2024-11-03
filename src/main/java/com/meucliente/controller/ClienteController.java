
package com.meucliente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.MensagemDTO;
import com.meucliente.service.ClienteService;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
  
  @Autowired
  private ClienteService service;

  @AuthorizationRequired
  @GetMapping(value="/", produces="application/json")
  public ResponseEntity<List<ClienteDTO>> findAll() {
    return service.findAll();
  }

  @AuthorizationRequired
  @GetMapping(value="/{cpf}", produces="application/json")
  public ResponseEntity<ClienteDTO> findCpf(@PathVariable String cpf) {
    return service.findCpf(cpf);
  }

  @AuthorizationRequired
  @GetMapping(value="/nome/{nome}", produces="application/json")
  public ResponseEntity<List<ClienteDTO>> findNome(@PathVariable String nome) {
    return service.findNome(nome);
  }

  @AuthorizationRequired
  @PostMapping("/")
  public ResponseEntity<MensagemDTO> adicionarCliente(@RequestBody ClienteDTO cliente) {
    return service.add(cliente);
  }

  @AuthorizationRequired
  @PutMapping("/")
  public ResponseEntity<MensagemDTO> modificarCliente(@RequestBody ClienteDTO cliente) {
    return service.update(cliente);
  }

  @AuthorizationRequired
  @DeleteMapping("/{cpf}")
  public ResponseEntity<MensagemDTO> deleteCliente(@PathVariable String cpf) {
    return service.delete(cpf);
  }

  @AuthorizationRequired
  @DeleteMapping("/")
  public ResponseEntity<MensagemDTO> deleteAll() {
    return service.deleteAll();
  }
}