package com.meucliente.dto;

import lombok.Getter;
import lombok.Setter;

public class UsuarioDTO {
  @Getter @Setter private String login;
  @Getter @Setter private String senha;
  
  public UsuarioDTO() {
    
  }
  
  public UsuarioDTO(String matricula) {
    this.setLogin(login);
  }
}
