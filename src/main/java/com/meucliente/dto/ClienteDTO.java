package com.meucliente.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

public class ClienteDTO {

	@Getter @Setter private String cpf;
	@Getter @Setter private String nome;
	@Getter @Setter private String cep;
	@Getter @Setter private String logradouro;
	@Getter @Setter private String bairro;
	@Getter @Setter private String cidade;
	@Getter @Setter private String uf;
	@Getter @Setter private String complemento;
	@Getter @Setter private Set<EmailDTO> emails;
	@Getter @Setter private Set<TelefoneDTO> telefones;

	public ClienteDTO() {

	}

	public ClienteDTO(String cpf) {
		this.setCpf(cpf);
	}

}
