package com.meucliente.dto;

import lombok.Getter;
import lombok.Setter;

public class CepDTO {

	@Getter @Setter private String cep;
	@Getter @Setter private String logradouro;
	@Getter @Setter private String complemento;
	@Getter @Setter private String bairro;
	@Getter @Setter private String localidade;
	@Getter @Setter private String uf;
	@Getter @Setter private String ibge;
	@Getter @Setter private String gia;
	@Getter @Setter private String ddd;
	@Getter @Setter private String siafi;

}