package com.meucliente.dto;

import lombok.Getter;
import lombok.Setter;

public class TelefoneDTO {

	@Getter @Setter private Long id;
	@Getter @Setter private String tipo;
	@Getter @Setter private String numero;

	public TelefoneDTO() {
	}

	public TelefoneDTO(String tipo, String numero) {
		this.setTipo(tipo);
		this.setNumero(numero);
	}
}
