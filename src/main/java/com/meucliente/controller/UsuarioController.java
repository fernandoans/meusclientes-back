package com.meucliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meucliente.dto.MensagemDTO;
import com.meucliente.dto.UsuarioDTO;
import com.meucliente.service.UsuarioService;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/login")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping("/")
	public ResponseEntity<MensagemDTO> logar(@RequestBody UsuarioDTO usuario) {
		return service.logar(usuario);
	}
}
