package com.meucliente.business;

import com.meucliente.business.enums.CodBusinessUsuario;
import com.meucliente.dto.UsuarioDTO;

public class UsuarioBusiness {

	private UsuarioBusiness() {
	}

	public static CodBusinessUsuario verificar(UsuarioDTO usuario) {
		if (FuncoesBusiness.naoContemValor(usuario.getLogin())) {
			return CodBusinessUsuario.LOGIN_OBRIGATORIO;
		}
		if (FuncoesBusiness.naoContemValor(usuario.getSenha())) {
			return CodBusinessUsuario.SENHA_OBRIGATORIA;
		}
		return CodBusinessUsuario.OK;
	}

}
