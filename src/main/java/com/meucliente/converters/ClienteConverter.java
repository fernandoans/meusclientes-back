package com.meucliente.converters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.meucliente.business.FuncoesBusiness;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.EmailDTO;
import com.meucliente.dto.TelefoneDTO;
import com.meucliente.model.Cliente;
import com.meucliente.model.Email;
import com.meucliente.model.Telefone;

@Service
public class ClienteConverter {

	public Cliente convertToEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setCpf(FuncoesBusiness.removerMascaraCPF(clienteDTO.getCpf()));
		cliente.setNome(clienteDTO.getNome());
		cliente.setCep(FuncoesBusiness.removerMascaraCEP(clienteDTO.getCep()));
		cliente.setLogradouro(clienteDTO.getLogradouro());
		cliente.setBairro(clienteDTO.getBairro());
		cliente.setCidade(clienteDTO.getCidade());
		cliente.setUf(clienteDTO.getUf());
		cliente.setComplemento(clienteDTO.getComplemento());

		// Obter os telefones
		Telefone tel;
		List<Telefone> telefones = new ArrayList<>();
		Set<TelefoneDTO> telefonesDto = clienteDTO.getTelefones();
		for (TelefoneDTO telDto : telefonesDto) {
			tel = new Telefone();
			if (FuncoesBusiness.contemValor(telDto.getId())) {
				tel.setId(telDto.getId());
			}
			tel.setNumero(FuncoesBusiness.removerMascaraTelefone(telDto.getNumero()));
			tel.setTipo(telDto.getTipo());
			tel.setCpf(FuncoesBusiness.removerMascaraCPF(clienteDTO.getCpf()));
			telefones.add(tel);
		}
		cliente.setTelefones(telefones);

		// Obter os emails
		Email ema;
		List<Email> emails = new ArrayList<>();
		Set<EmailDTO> emailsDto = clienteDTO.getEmails();
		for (EmailDTO emaDto : emailsDto) {
			ema = new Email();
			if (FuncoesBusiness.contemValor(emaDto.getId())) {
				ema.setId(emaDto.getId());
			}
			ema.setCpf(FuncoesBusiness.removerMascaraCPF(clienteDTO.getCpf()));
			ema.setDescricao(emaDto.getDescricao());
			emails.add(ema);
		}
		cliente.setEmails(emails);

		return cliente;
	}

	public ClienteDTO convertToDto(Cliente cliente) {
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setCpf(FuncoesBusiness.aplicarMascaraCPF(cliente.getCpf()));
		clienteDto.setNome(cliente.getNome());
		clienteDto.setCep(FuncoesBusiness.aplicarMascaraCEP(cliente.getCep()));
		clienteDto.setLogradouro(cliente.getLogradouro());
		clienteDto.setBairro(cliente.getBairro());
		clienteDto.setCidade(cliente.getCidade());
		clienteDto.setUf(cliente.getUf());
		clienteDto.setComplemento(cliente.getComplemento());

		// Obter os telefones
		TelefoneDTO telDto;
		Set<TelefoneDTO> telefonesDto = new HashSet<>();
		List<Telefone> telefones = cliente.getTelefones();
		for (Telefone tel : telefones) {
			telDto = new TelefoneDTO();
			if (FuncoesBusiness.contemValor(tel.getId())) {
				telDto.setId(tel.getId());
			}
			telDto.setTipo(tel.getTipo());
			telDto.setNumero(FuncoesBusiness.aplicarMascaraTelefone(tel.getNumero(),tel.getTipo().charAt(0)));
			telefonesDto.add(telDto);
		}
		clienteDto.setTelefones(telefonesDto);

		// Obter os emails
		EmailDTO emaDto;
		Set<EmailDTO> emailsDto = new HashSet<>();
		List<Email> emails = cliente.getEmails();
		for (Email ema : emails) {
			emaDto = new EmailDTO();
			if (FuncoesBusiness.contemValor(ema.getId())) {
				emaDto.setId(ema.getId());
			}
			emaDto.setDescricao(ema.getDescricao());
			emailsDto.add(emaDto);
		}
		clienteDto.setEmails(emailsDto);

		return clienteDto;
	}

}
