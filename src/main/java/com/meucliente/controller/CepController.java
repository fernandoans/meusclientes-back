package com.meucliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meucliente.dto.CepDTO;
import com.meucliente.service.CepService;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping(value = "/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("/{cep}")
    public ResponseEntity<CepDTO> consultarCep(@PathVariable String cep) {
    	return cepService.consultarCep(cep);
    }
}