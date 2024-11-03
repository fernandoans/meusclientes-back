package com.meucliente.dto;

import lombok.Getter;
import lombok.Setter;

public class EmailDTO {

	@Getter
	@Setter
	private Long id;
	@Getter
	@Setter
	private String descricao;

	public EmailDTO() {

	}

	public EmailDTO(String descricao) {
		this.setDescricao(descricao);
	}
}