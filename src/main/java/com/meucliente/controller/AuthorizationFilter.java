package com.meucliente.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.meucliente.business.enums.CodBusinessSeguranca;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.StringTokenizer;

@Component
public class AuthorizationFilter implements HandlerInterceptor {
	
	private String permissao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            if (method.isAnnotationPresent(AuthorizationRequired.class)) {
                String authorizationHeader = request.getHeader("Authorization");

                if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, CodBusinessSeguranca.TOKEN_INVALIDO.getDescricao());
                    return false;
                }

                // Extrai o token, removendo a parte "Bearer "
                String token = authorizationHeader.substring(7);

                // Validar o token
                try {
	                if (!isValidToken(token)) {
	                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, CodBusinessSeguranca.TOKEN_INVALIDO.getDescricao());
	                    return false;
	                }
	                // Determinar se a requisição é para um método restrito
	                String modo = request.getMethod();
	                boolean isWriteMethod = "POST".equalsIgnoreCase(modo) || "PUT".equalsIgnoreCase(modo) || "DELETE".equalsIgnoreCase(modo);

	                // Verificar permissão
	                if (isWriteMethod && !CodBusinessSeguranca.PERMISSSAO_TOTAL.getDescricao().equals(permissao)) {
	                    response.sendError(HttpServletResponse.SC_FORBIDDEN, CodBusinessSeguranca.SEM_PERMISSAO_ESCRITA.getDescricao());
	                    return false;
	                }	                
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, CodBusinessSeguranca.TOKEN_INVALIDO.getDescricao());
                    return false;
                }
            }
        }
        return true;
    }

    // Validar o token
    private boolean isValidToken(String token) {
    	StringTokenizer tokenizer = new StringTokenizer(decrBase64(token), ":");
    	byte pos = 0;
    	String tipo = "", data = "";
        while (tokenizer.hasMoreElements()) {
        	switch (pos) {
        	case 0: tokenizer.nextToken(); break; // TokenBearer
        	case 1: tokenizer.nextToken(); break; // nome
        	case 2: tipo = tokenizer.nextToken(); break; 
        	case 3: data = tokenizer.nextToken(); break;
        	}
        	pos += 1;
        }
        if (System.currentTimeMillis() > Long.parseLong(data)) {
        	return false;
        }
        permissao = tipo;
        return true;
    }

    // Descriptografar em Base64
    public String decrBase64(String valor) {
        if (valor == null || valor.isEmpty()) {
            throw new IllegalArgumentException("A senha encriptada não pode ser nula ou vazia.");
        }
        // Decodificar o Base64 e converter de volta para uma string
        byte[] decodedBytes = Base64.getDecoder().decode(valor);
        return new String(decodedBytes);
    }    
}