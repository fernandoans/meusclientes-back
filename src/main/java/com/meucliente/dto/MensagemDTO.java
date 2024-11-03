package com.meucliente.dto;

import com.meucliente.business.enums.CodBusinessCliente;
import com.meucliente.business.enums.CodBusinessEmail;
import com.meucliente.business.enums.CodBusinessUsuario;
import com.meucliente.business.enums.CodBusinessTelefone;

import lombok.Getter;
import lombok.Setter;

public class MensagemDTO {
  @Getter @Setter private String messagem;
  
  public MensagemDTO() {
  }
  
  public MensagemDTO(String messagem) {
    this.setMessagem(messagem);
  }
  
  // Mensagens de Negócio

  public MensagemDTO(CodBusinessCliente en) {
    this.setMessagem(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessUsuario en) {
    this.setMessagem(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessEmail en) {
    this.setMessagem(en.getDescricao());
  }  

  public MensagemDTO(CodBusinessTelefone en) {
    this.setMessagem(en.getDescricao());
  }  
}
