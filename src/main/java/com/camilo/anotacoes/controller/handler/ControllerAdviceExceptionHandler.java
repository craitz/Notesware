package com.camilo.anotacoes.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.camilo.anotacoes.service.exception.CategoriaJaCadastradaException;
import com.camilo.anotacoes.service.exception.GrupoJaCadastradoException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(CategoriaJaCadastradaException.class)
	public ResponseEntity<String> handleCategoriaJaCadastradaException(CategoriaJaCadastradaException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(GrupoJaCadastradoException.class)
	public ResponseEntity<String> handleGrupoJaCadastradoException(GrupoJaCadastradoException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
