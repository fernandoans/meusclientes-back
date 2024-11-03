package com.meucliente.business.enums;

public enum CodBusinessUsuario {
	OK("Sem problemas."), 
	LOGIN_OBRIGATORIO("Login não foi informada. "), 
	SENHA_OBRIGATORIA("Senha não foi informada. ");

	private String descricao;

	CodBusinessUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
