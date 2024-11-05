package com.meucliente.business;

import java.util.Set;

import com.meucliente.business.enums.CodBusinessCliente;
import com.meucliente.business.enums.CodBusinessEmail;
import com.meucliente.business.enums.CodBusinessTelefone;
import com.meucliente.dto.ClienteDTO;
import com.meucliente.dto.EmailDTO;
import com.meucliente.dto.TelefoneDTO;

public class ClienteBusiness {

	private ClienteBusiness() {
	}

	public static String verificar(ClienteDTO cliente) {
		StringBuilder mens = new StringBuilder("");

		if (FuncoesBusiness.naoContemValor(cliente.getCpf())) {
			mens.append(CodBusinessCliente.CPF_OBRIGATORIO.getDescricao());
		} else {
			if (!FuncoesBusiness.isValidarCPF(cliente.getCpf())) {
				mens.append(CodBusinessCliente.CPF_MALFORMADO.getDescricao());
			}
		}
		if (FuncoesBusiness.naoContemValor(cliente.getNome())) {
			mens.append(CodBusinessCliente.NOME_OBRIGATORIO.getDescricao());
		} else {
			if (cliente.getNome().length() < 3) {
				mens.append(CodBusinessCliente.NOME_3LET_OBRIGATORIO.getDescricao());
			}
		}
		if (FuncoesBusiness.naoContemValor(cliente.getCep())) {
			mens.append(CodBusinessCliente.CEP_OBRIGATORIO.getDescricao());
		} else {
			if (!FuncoesBusiness.isValidarCep(cliente.getCep())) {
				mens.append(CodBusinessCliente.CEP_MALFORMADO.getDescricao());
			}
		}
		if (FuncoesBusiness.naoContemValor(cliente.getLogradouro())) {
			mens.append(CodBusinessCliente.LOGRADOURO_OBRIGATORIO.getDescricao());
		}
		if (FuncoesBusiness.naoContemValor(cliente.getBairro())) {
			mens.append(CodBusinessCliente.BAIRRO_OBRIGATORIO.getDescricao());
		}
		if (FuncoesBusiness.naoContemValor(cliente.getCidade())) {
			mens.append(CodBusinessCliente.CIDADE_OBRIGATORIA.getDescricao());
		}
		if (FuncoesBusiness.naoContemValor(cliente.getUf())) {
			mens.append(CodBusinessCliente.UF_OBRIGATORIA.getDescricao());
		}
		if (FuncoesBusiness.naoContemValor(cliente.getEmails())) {
			mens.append(CodBusinessCliente.EMAIL_OBRIGATORIO.getDescricao());
		} else {
			mens.append(validarEmails(cliente.getEmails()));
		}
		if (FuncoesBusiness.naoContemValor(cliente.getTelefones())) {
			mens.append(CodBusinessCliente.TELEFONE_OBRIGATORIO.getDescricao());
		} else {
			mens.append(validarTelefones(cliente.getTelefones()));
		}
		if (mens.isEmpty()) {
			return CodBusinessCliente.OK.getDescricao();
		}
		return mens.toString();
	}

	// Métodos para validar as classes auxiliares

	private static String validarEmails(Set<EmailDTO> emailsDto) {
		for (EmailDTO emailDto : emailsDto) {
			CodBusinessEmail codBusiness = EmailBusiness.verificar(emailDto);
			if (codBusiness != CodBusinessEmail.OK) {
				return codBusiness.getDescricao();
			}
		}
		return "";
	}

	private static String validarTelefones(Set<TelefoneDTO> telefonesDto) {
		for (TelefoneDTO telefoneDto : telefonesDto) {
			CodBusinessTelefone codBusiness = TelefoneBusiness.verificar(telefoneDto);
			if (codBusiness != CodBusinessTelefone.OK) {
				return codBusiness.getDescricao();
			}
		}
		return "";
	}
}
