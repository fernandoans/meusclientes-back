package com.meucliente;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.meucliente.business.enums.CodBusinessUsuario;
import com.meucliente.dto.MensagemDTO;
import com.meucliente.dto.UsuarioDTO;

class UsuarioTest extends PaiTest {

	public UsuarioTest() {
		super();
	}

	@Test
	@DisplayName("Usuário sem login")
	@Order(32)
	void testLogarErrado1() {
		UsuarioDTO objDTO = new UsuarioDTO();
		objDTO.setLogin("");
		objDTO.setSenha("MTIzcXdlMTIz");
		ResponseEntity<MensagemDTO> response = usuarioCt.logar(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessUsuario.LOGIN_OBRIGATORIO.getDescricao(), msg), "Problema para cadastrar");
	}

	@Test
	@DisplayName("Usuário sem senha")
	@Order(33)
	void testLogarErrado2() {
		UsuarioDTO objDTO = new UsuarioDTO();
		objDTO.setLogin("SeuJoão");
		objDTO.setSenha("");
		ResponseEntity<MensagemDTO> response = usuarioCt.logar(objDTO);
		String msg = response.getBody().getMessagem();
		assertTrue(compStr(CodBusinessUsuario.SENHA_OBRIGATORIA.getDescricao(), msg), "Problema para cadastrar");
	}
}
