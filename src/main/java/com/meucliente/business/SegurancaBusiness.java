package com.meucliente.business;

import java.util.Base64;

import com.meucliente.business.enums.CodBusinessSeguranca;
import com.meucliente.dto.UsuarioDTO;

public class SegurancaBusiness {
	private static final long EXPIRATION_TIME = 86400000; // 1 dia
	
	private SegurancaBusiness() {
	}

    // Criptografar em Base64
    public static String encrBase64(String valor) {
        if (valor == null || valor.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia.");
        }
        // Converter o valor para bytes e aplicar a codificação Base64
        return Base64.getEncoder().encodeToString(valor.getBytes());
    }
	
	public static String gerarToken(UsuarioDTO usuario) {
		if (usuario.getLogin().equals("admin") && (usuario.getSenha().equals(SegurancaBusiness.encrBase64("123qwe!@#")))) {
			return SegurancaBusiness.retornaToken(usuario.getLogin(), CodBusinessSeguranca.PERMISSSAO_TOTAL);
		}
		if (usuario.getLogin().equals("padrão") && (usuario.getSenha().equals(SegurancaBusiness.encrBase64("123qwe123")))) {
			return SegurancaBusiness.retornaToken(usuario.getLogin(), CodBusinessSeguranca.PERMISSSAO_LEITURA);
		}
		return CodBusinessSeguranca.SEM_PERMISSAO.getDescricao();
	}    
    
    public static String retornaToken(String nome, CodBusinessSeguranca tipo) {
		return SegurancaBusiness.encrBase64("TokenBearer:" + nome + ":" + 
				tipo.getDescricao() + ":" + (System.currentTimeMillis() + EXPIRATION_TIME));
	}
}
