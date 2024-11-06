package com.meucliente.business;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FuncoesBusiness {
	
	// Expressão regular para CPF
	private static final String CPF_REGEX = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})$";
	// Expressão regular para CEP
	private static final String CEP_REGEX = "^\\d{5}-\\d{3}$";
	// Expressão regular para celular (9 dígitos)
	private static final String CELULAR_REGEX = "^\\d{5}-\\d{4}";
	// Expressão regular para comercial (8 dígitos)
	private static final String COMMERCIAL_REGEX = "^\\d{4}-\\d{4}$";
	// Expressão regular para email
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

	private static final Pattern CPF_PATTERN = Pattern.compile(CPF_REGEX);
	private static final Pattern CEP_PATTERN = Pattern.compile(CEP_REGEX);
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
	private static final Pattern CELULAR_PATTERN = Pattern.compile(CELULAR_REGEX);
	private static final Pattern COMMERCIAL_PATTERN = Pattern.compile(COMMERCIAL_REGEX);

	private FuncoesBusiness() {
	}
	
	// Verificar a presença dos Valores
	
	public static boolean naoContemValor(String st) {
		return st == null || st.trim().length() == 0;
	}

	public static boolean naoContemValor(Set<?> lst) {
		return lst == null || lst.size() == 0;
	}

	public static boolean contemValor(Long lg) {
		return lg != null && lg.longValue() > 0;
	}

	// CAMPO CPF

	public static String aplicarMascaraCPF(String cpf) {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
	}

	public static String removerMascaraCPF(String cpfComMascara) {
		return cpfComMascara.replace(".", "").replace("-", "");
	}

	// CAMPO CEP

	public static String aplicarMascaraCEP(String cep) {
		return cep.substring(0, 5) + "-" + cep.substring(5, 8);
	}

	public static String removerMascaraCEP(String cepComMascara) {
		return cepComMascara.replace("-", "");
	}

	// CAMPO TELEFONE

	public static String aplicarMascaraTelefone(String telefone, char tipo) {
		if (tipo == 'C') {
			return telefone.substring(0, 5) + "-" + telefone.substring(5);
		}
		return telefone.substring(0, 4) + "-" + telefone.substring(4);
	}

	public static String removerMascaraTelefone(String telefoneComMascara) {
		return telefoneComMascara.replace("-", "");
	}

	public static boolean isValidarCPF(String numero) {
		Matcher matcher = CPF_PATTERN.matcher(numero);
		return matcher.matches();
	}

	public static boolean isValidarCep(String numero) {
		Matcher matcher = CEP_PATTERN.matcher(numero);
		return matcher.matches();
	}

	public static boolean isValidarCelular(String numero) {
		Matcher matcher = CELULAR_PATTERN.matcher(numero);
		return matcher.matches();
	}

	public static boolean isValidarComercial(String numero) {
		Matcher matcher = COMMERCIAL_PATTERN.matcher(numero);
		return matcher.matches();
	}

	// CAMPO EMAIL
	
	public static boolean isValidarEmail(String email) {
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
}
