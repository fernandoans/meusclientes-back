package com.meucliente.business.enums;

public enum CodBusinessTelefone {
  OK("Sem problemas."),
  TIPO_OBRIGATORIO("Tipo do Telefone não foi informado. "),
  TIPO_ERRADO("Tipo do Telefone incorreto. "),
  NUMERO_OBRIGATORIO("Número do Telefone não foi informado. "),
  TELEFONE_INCOMPLETO("Quantidade de Dígitos do telefone errada. "),
  TELEFONE_MALFORMADO("Número do telefone inválido, fora do padrão. ");
  
  private String descricao;

  CodBusinessTelefone(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
