package com.meucliente.business.enums;

public enum CodBusinessSeguranca {

	PERMISSSAO_TOTAL("TOTAL"),
	PERMISSSAO_LEITURA("LEITURA"),
	SEM_PERMISSAO("Este usuário não possui permissão."),
	TOKEN_INVALIDO("Token de autorização inválido."),
	SEM_PERMISSAO_ESCRITA("Usuário só possui permissão para visualizar os dados.");

	private String descricao;

	CodBusinessSeguranca(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
