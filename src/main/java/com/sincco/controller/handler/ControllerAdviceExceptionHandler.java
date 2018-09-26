package com.sincco.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sincco.service.exception.EmailJaCadastradoExcetion;
import com.sincco.service.exception.NomeEstiloJaCadastradoExcetion;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	@ExceptionHandler(NomeEstiloJaCadastradoExcetion.class)
	public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoExcetion e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	@ExceptionHandler(EmailJaCadastradoExcetion.class)
	public ResponseEntity<String> handleEmailJaCadastradoException(EmailJaCadastradoExcetion e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
