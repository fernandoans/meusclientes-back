package com.meucliente.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.meucliente.business.SegurancaBusiness;
import com.meucliente.business.UsuarioBusiness;
import com.meucliente.business.enums.CodBusinessUsuario;
import com.meucliente.dto.MensagemDTO;
import com.meucliente.dto.UsuarioDTO;

@Service
public class UsuarioService {
  
  public ResponseEntity<MensagemDTO> logar(UsuarioDTO usuarioDTO) {
    try {
      CodBusinessUsuario codBusiness = UsuarioBusiness.verificar(usuarioDTO);
      if (codBusiness == CodBusinessUsuario.OK) {
        return new ResponseEntity<>(new MensagemDTO(SegurancaBusiness.gerarToken(usuarioDTO)), HttpStatus.OK);
      } 
      return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }  
}
