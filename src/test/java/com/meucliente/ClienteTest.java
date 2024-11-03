package com.meucliente;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.meucliente.business.enums.CodBusinessCliente;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.MensagemDTO;

class ClienteTest extends PaiTest {

	public ClienteTest() {
		super();
	}

	@Test
	@DisplayName("Cliente sem CPF")
	@Order(10)
	void testClienteSemCpf() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setCpf("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.CPF_OBRIGATORIO.getDescricao(), msg), "Não verificado CPF vazio");
	}

	@Test
	@DisplayName("Cliente sem nome")
	@Order(11)
	void testClienteSemNome() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setNome("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.NOME_OBRIGATORIO.getDescricao(), msg), "Não verificado NOME vazio");
	}

	@Test
	@DisplayName("Cliente com nome menor que 3 caracteres")
	@Order(12)
	void testClienteNomePequeno() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setNome("AB");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.NOME_3LET_OBRIGATORIO.getDescricao(), msg), "Não verificado NOME menor que 3 caracteres");
	}

	@Test
	@DisplayName("Cliente sem CEP")
	@Order(13)
	void testClienteSemCep() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setCep("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.CEP_OBRIGATORIO.getDescricao(), msg), "Não verificado CEP");
	}

	@Test
	@DisplayName("Cliente sem Logradouro")
	@Order(14)
	void testClienteSemLogradouro() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setLogradouro("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.LOGRADOURO_OBRIGATORIO.getDescricao(), msg), "Não verificado Logradouro");
	}

	@Test
	@DisplayName("Cliente sem Bairro")
	@Order(15)
	void testClienteSemBairro() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setBairro("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.BAIRRO_OBRIGATORIO.getDescricao(), msg), "Não verificado Bairro");
	}

	@Test
	@DisplayName("Cliente sem Cidade")
	@Order(16)
	void testClienteSemCidade() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setCidade("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.CIDADE_OBRIGATORIA.getDescricao(), msg), "Não verificado Cidade");
	}

	@Test
	@DisplayName("Cliente sem UF")
	@Order(17)
	void testClienteSemUF() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setUf("");
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.UF_OBRIGATORIA.getDescricao(), msg), "Não verificado UF");
	}

	@Test
	@DisplayName("Cliente sem E-mail")
	@Order(18)
	void testClienteSemEmails() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setEmails(null);
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.EMAIL_OBRIGATORIO.getDescricao(), msg), "Não verificado E-mails");
	}

	@Test
	@DisplayName("Cliente sem Telefones")
	@Order(19)
	void testClienteSemTelefones() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setTelefones(null);
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessCliente.TELEFONE_OBRIGATORIO.getDescricao(), msg), "Não verificado Telefones");
	}

}
