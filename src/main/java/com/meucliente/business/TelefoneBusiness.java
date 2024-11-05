package com.meucliente.business;

import com.meucliente.business.enums.CodBusinessTelefone;
import com.meucliente.dto.TelefoneDTO;

public class TelefoneBusiness {

	private TelefoneBusiness() {
	}

	public static CodBusinessTelefone verificar(TelefoneDTO telefone) {
		if (FuncoesBusiness.naoContemValor(telefone.getTipo())) {
			return CodBusinessTelefone.TIPO_OBRIGATORIO;
		}
		if ("RCT".indexOf(telefone.getTipo().charAt(0)) == -1) {
			return CodBusinessTelefone.TIPO_ERRADO;
		}
		if (FuncoesBusiness.naoContemValor(telefone.getNumero())) {
			return CodBusinessTelefone.NUMERO_OBRIGATORIO;
		}
		if (telefone.getTipo().charAt(0) == 'C') {
			// Considerando 1 digito a mais por causa da máscara
			if (telefone.getNumero().length() < 10) {
				return CodBusinessTelefone.TELEFONE_INCOMPLETO;
			}
			if (!FuncoesBusiness.isValidarCelular(telefone.getNumero())) {
				return CodBusinessTelefone.TELEFONE_MALFORMADO;
			}
		} else {
			// Considerando 1 digito a mais por causa da máscara
			if (telefone.getNumero().length() < 9) {
				return CodBusinessTelefone.TELEFONE_INCOMPLETO;
			}
			if (!FuncoesBusiness.isValidarComercial(telefone.getNumero())) {
				return CodBusinessTelefone.TELEFONE_MALFORMADO;
			}
		}
		return CodBusinessTelefone.OK;
	}
	
}
