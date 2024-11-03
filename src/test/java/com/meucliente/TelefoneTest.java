package com.meucliente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.meucliente.business.enums.CodBusinessTelefone;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.MensagemDTO;

class TelefoneTest extends PaiTest {

	public TelefoneTest() {
		super();
	}

	@Test
	@DisplayName("Tipo de Telefone não foi informado")
	@Order(24)
	void testTipoTelefoneNaoInformado() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setTelefones(popTelefone(objDTO,"", "56765473"));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessTelefone.TIPO_OBRIGATORIO.getDescricao(), msg), "Não verificado Tipo de Telefone vazio");
	}

	@Test
	@DisplayName("Tipo de Telefone informado errado")
	@Order(25)
	void testTipoTelefoneErrado() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setTelefones(popTelefone(objDTO,"H", "56765473"));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessTelefone.TIPO_ERRADO.getDescricao(), msg), "Não verificado Tipo de Telefone errado");
	}

	@Test
	@DisplayName("Numero de Telefone não foi informado")
	@Order(26)
	void testNumeroTelefoneNaoInformado() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setTelefones(popTelefone(objDTO,"T", ""));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessTelefone.NUMERO_OBRIGATORIO.getDescricao(), msg), "Não verificado Número de Telefone vazio");
	}
	
	@Test
	@DisplayName("Telefone Trabalho pequeno")
	@Order(27)
	void testNumeroTelefoneTrabalhoPequeno() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setTelefones(popTelefone(objDTO,"T", "87543"));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessTelefone.TELEFONE_INCOMPLETO.getDescricao(), msg), "Não verificado Telefone incompleto");
	}
	
	@Test
	@DisplayName("Telefone Trabalho mal formado")
	@Order(27)
	void testNumeroTelefoneTrabalhoMalFormado() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setTelefones(popTelefone(objDTO,"T", "87543AEF"));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessTelefone.TELEFONE_MALFORMADO.getDescricao(), msg), "Não verificado Telefone mal formado");
	}
}
