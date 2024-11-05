package com.meucliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.meucliente.business.FuncoesBusiness;
import com.meucliente.dto.CepDTO;

import reactor.core.publisher.Mono;

@Service
public class CepService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ResponseEntity<CepDTO> consultarCep(String cep) {
		if (FuncoesBusiness.naoContemValor(cep)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (FuncoesBusiness.isValidarCep(cep)) {
			cep = FuncoesBusiness.removerMascaraCEP(cep);
	        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
	        Mono<CepDTO> response = webClientBuilder.build()
	                .get()
	                .uri(url)
	                .retrieve()
	                .bodyToMono(CepDTO.class);
	        return new ResponseEntity<>(response.block(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}