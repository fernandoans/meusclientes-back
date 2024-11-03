package com.meucliente;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.meucliente.controller.ClienteController;
import com.meucliente.controller.UsuarioController;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.EmailDTO;
import com.meucliente.dto.TelefoneDTO;

@SpringBootTest(classes = LaboratorioApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PaiTest {

	@Autowired
	WebApplicationContext context;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	ClienteController clienteCt;

	@Autowired
	UsuarioController usuarioCt;

	@BeforeEach
	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	@DisplayName("Verificar Controllers")
	@Order(1)
	void testContextLoads() {
		assertThat(clienteCt).isNotNull();
		assertThat(usuarioCt).isNotNull();
	}

	protected boolean compStr(String msg1, String msg2) {
		return msg1.equals(msg2);
	}

	// Montagens dos Objetos

	protected ClienteDTO montarClienteCompleto() {
		ClienteDTO objDTO = new ClienteDTO();
		objDTO.setCpf("616.960.960-57");
		objDTO.setNome("Fulano da Silva");
		objDTO.setCep("71680-389");
		objDTO.setLogradouro("Condominio Quintas da Alvorada");
		objDTO.setBairro("Setor Habitacional Jardim Botânico");
		objDTO.setCidade("Brasília");
		objDTO.setUf("DF");
		objDTO.setComplemento("Rua dos Bobos Nº0");
		// Adiciona Email
		objDTO.setEmails(popEmail(null, "fernando.anselmo74@gmail.com"));
		// Adiciona Telefone
		objDTO.setTelefones(popTelefone(null, "C", "998740763"));
		objDTO.setTelefones(popTelefone(objDTO, "R", "32015834"));
		return objDTO;
	}
	
	protected Set<EmailDTO> popEmail(ClienteDTO objDTO, String descricao) {
		Set<EmailDTO> emails;
		if (objDTO == null) {
			emails = new HashSet<>();
		} else {
			emails = objDTO.getEmails();
		}
		emails.add(new EmailDTO(descricao));
		return emails;
	}
	
	protected Set<TelefoneDTO> popTelefone(ClienteDTO objDTO, String tipo, String numero) {
		Set<TelefoneDTO> telefones;
		if (objDTO == null) {
			telefones = new HashSet<>();
		} else {
			telefones = objDTO.getTelefones();
		}
		telefones.add(new TelefoneDTO(tipo, numero));
		return telefones;
	}
	
}
