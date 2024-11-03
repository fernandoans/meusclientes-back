package com.meucliente.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.meucliente.business.ClienteBusiness;
import com.meucliente.business.FuncoesBusiness;
import com.meucliente.business.enums.CodBusinessCliente;
import com.meucliente.converters.ClienteConverter;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.MensagemDTO;
import com.meucliente.model.Cliente;
import com.meucliente.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private ClienteConverter converter;

	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clientes = (List<Cliente>) repository.findAll();
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente c: clientes) {
			clientesDTO.add(converter.convertToDto(c));
		}
		return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
	}

	public ResponseEntity<ClienteDTO> findCpf(String cpf) {
		if (cpf != null) {
			Optional<Cliente> optional = repository.findById(FuncoesBusiness.removerMascaraCPF(cpf));
			if (optional.isPresent()) {
				ClienteDTO clientesDTO = converter.convertToDto(optional.get());
				return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<ClienteDTO>> findNome(String nome) {
		if (nome != null) {
			List<Cliente> clientes = repository.obterPorNome("%" + nome + "%");
			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			List<ClienteDTO> clientesDTO = new ArrayList<>();
			for (Cliente c: clientes) {
				clientesDTO.add(converter.convertToDto(c));
			}
			return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<MensagemDTO> add(ClienteDTO clienteDTO) {
		try {
			String mens = ClienteBusiness.verificar(clienteDTO);
			if (mens.equals(CodBusinessCliente.OK.getDescricao())) {
				repository.save(converter.convertToEntity(clienteDTO));
				return new ResponseEntity<>(new MensagemDTO(CodBusinessCliente.INCLUIDO_OK), HttpStatus.OK);
			}
			return new ResponseEntity<>(new MensagemDTO(mens), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<MensagemDTO> update(ClienteDTO clienteDTO) {
		String mens = ClienteBusiness.verificar(clienteDTO);
		if (mens.equals(CodBusinessCliente.OK.getDescricao())) {
			Optional<Cliente> optional = repository.findById(FuncoesBusiness.removerMascaraCPF(clienteDTO.getCpf()));
			if (optional.isPresent()) {
				repository.save(converter.convertToEntity(clienteDTO));
				return new ResponseEntity<>(new MensagemDTO(CodBusinessCliente.ALTERADO_OK), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new MensagemDTO(mens), HttpStatus.NOT_ACCEPTABLE);
	}

	public ResponseEntity<MensagemDTO> delete(String cpf) {
		if (cpf == null) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		try {
			cpf = FuncoesBusiness.removerMascaraCPF(cpf);
			Optional<Cliente> optional = repository.findById(cpf);
			if (optional.isPresent()) {
				repository.deleteById(cpf);
				return new ResponseEntity<>(new MensagemDTO(CodBusinessCliente.EXCLUIDO_OK), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<MensagemDTO> deleteAll() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(new MensagemDTO(CodBusinessCliente.EXCLUIDO_ALL_OK), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
