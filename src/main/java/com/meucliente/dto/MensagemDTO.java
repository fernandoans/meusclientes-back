package com.meucliente.dto;

import java.util.Set;

import com.meucliente.business.enums.CodBusinessCliente;
import com.meucliente.business.enums.CodBusinessEmail;
import com.meucliente.business.enums.CodBusinessUsuario;
import com.meucliente.business.enums.CodBusinessTelefone;

import lombok.Getter;
import lombok.Setter;

public class MensagemDTO {
	@Getter @Setter private String mensagem;
	@Getter @Setter private String erro;

	public MensagemDTO() {
	}

	public MensagemDTO(String msg, char tipo) {
	  if (tipo == 'E') {
		  this.setErro(msg);
	  } else {
		  this.setMensagem(msg);
	  }
  }

	// Mensagens de Negócio

	public MensagemDTO(CodBusinessCliente cod) {
		if (Set.of(CodBusinessCliente.OK, CodBusinessCliente.INCLUIDO_OK, CodBusinessCliente.ALTERADO_OK, 
				CodBusinessCliente.EXCLUIDO_OK, CodBusinessCliente.EXCLUIDO_ALL_OK).contains(cod)) {
			this.setMensagem(cod.getDescricao());
		} else {
			this.setErro(cod.getDescricao());
		}
	}

	public MensagemDTO(CodBusinessUsuario cod) {
		if (cod == CodBusinessUsuario.OK) {
			this.setMensagem(cod.getDescricao());
		} else {
			this.setErro(cod.getDescricao());
		}
	}

	public MensagemDTO(CodBusinessEmail cod) {
		if (cod == CodBusinessEmail.OK) {
			this.setMensagem(cod.getDescricao());
		} else {
			this.setErro(cod.getDescricao());
		}
	}

	public MensagemDTO(CodBusinessTelefone cod) {
		if (cod == CodBusinessTelefone.OK) {
			this.setMensagem(cod.getDescricao());
		} else {
			this.setErro(cod.getDescricao());
		}
	}
}
