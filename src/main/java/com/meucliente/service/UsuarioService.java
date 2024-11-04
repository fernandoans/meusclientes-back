package com.meucliente.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.meucliente.business.SegurancaBusiness;
import com.meucliente.business.UsuarioBusiness;
import com.meucliente.business.enums.CodBusinessSeguranca;
import com.meucliente.business.enums.CodBusinessUsuario;
import com.meucliente.dto.MensagemDTO;
import com.meucliente.dto.UsuarioDTO;

@Service
public class UsuarioService {

	public ResponseEntity<MensagemDTO> logar(UsuarioDTO usuarioDTO) {
		try {
			CodBusinessUsuario codBusiness = UsuarioBusiness.verificar(usuarioDTO);
			if (codBusiness == CodBusinessUsuario.OK) {
				String token = SegurancaBusiness.gerarToken(usuarioDTO);
				if (token.equals(CodBusinessSeguranca.SEM_PERMISSAO.getDescricao())) {
					return new ResponseEntity<>(new MensagemDTO(token, 'E'), HttpStatus.NOT_ACCEPTABLE);
				}
				return new ResponseEntity<>(new MensagemDTO(token, 'M'), HttpStatus.OK);
			}
			return new ResponseEntity<>(new MensagemDTO(codBusiness), HttpStatus.NOT_ACCEPTABLE);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
