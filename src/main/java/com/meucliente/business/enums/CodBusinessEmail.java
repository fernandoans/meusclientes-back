package com.meucliente.business.enums;

public enum CodBusinessEmail {
  OK("Sem problemas."),
  EMAIL_NAO_INFORMADO("E-mail não foi informado. "),
  EMAIL_MALFORMADO("E-mail informado incorretamente. ");
  
  private String descricao;

  CodBusinessEmail(String descricao) {
      this.descricao = descricao;
  }

  public String getDescricao() {
      return descricao;
  }  
}
