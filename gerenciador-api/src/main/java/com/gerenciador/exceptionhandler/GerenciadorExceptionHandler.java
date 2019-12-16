package com.gerenciador.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gerenciador.erromessage.Erro;

@ControllerAdvice
public class GerenciadorExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource message;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgUsuario = message.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String msgDesenv = ex.getCause().toString();
		
		List<Erro> erros = Arrays.asList(new Erro(msgUsuario, msgDesenv));
		
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
		
	}
	
}