package com.meucliente.business.enums;

public enum CodBusinessCliente {
	OK("Sem problemas."), 
	INCLUIDO_OK("Cliente criado com sucesso. "), 
	ALTERADO_OK("Cliente modificado com sucesso. "),
	EXCLUIDO_OK("Cliente eliminado com sucesso. "), 
	EXCLUIDO_ALL_OK("Todos os Cliente eliminados. "),
	NOME_OBRIGATORIO("Nome não foi informado. "), 
	NOME_3LET_OBRIGATORIO("Nome precisa de um mínimo de 3 caracteres. "),
	CPF_OBRIGATORIO("CPF não foi informado. "), 
	CEP_OBRIGATORIO("CEP não foi informado. "),
	LOGRADOURO_OBRIGATORIO("Logradouro não foi informado. "), 
	BAIRRO_OBRIGATORIO("Bairro não foi informado. "),
	CIDADE_OBRIGATORIA("Cidade não foi informado. "), 
	UF_OBRIGATORIA("UF não foi informado. "), 
	EMAIL_OBRIGATORIO("Nenhum e-mail foi informado. "), 
	TELEFONE_OBRIGATORIO("Nenhum telefone foi informado. ");

	private String descricao;

	CodBusinessCliente(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
