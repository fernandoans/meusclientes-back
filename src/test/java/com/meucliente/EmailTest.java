package com.meucliente;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.meucliente.business.enums.CodBusinessEmail;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.MensagemDTO;

class EmailTest extends PaiTest {

	public EmailTest() {
		super();
	}

	@Test
	@DisplayName("E-mail não foi informado")
	@Order(20)
	void testEmailNaoInformado() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setEmails(popEmail(objDTO,""));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessEmail.EMAIL_NAO_INFORMADO.getDescricao(), msg), "Não verificado e-mail vazio");
	}

	@Test
	@DisplayName("E-mail mal formado")
	@Order(21)
	void testEmailMalInformado() {
		ClienteDTO objDTO = montarClienteCompleto();
		objDTO.setEmails(popEmail(objDTO,"joaodasilva.com.br"));
		ResponseEntity<MensagemDTO> response = clienteCt.adicionarCliente(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessEmail.EMAIL_MALFORMADO.getDescricao(), msg), "Não verificado e-mail mal formado");
	}
}
