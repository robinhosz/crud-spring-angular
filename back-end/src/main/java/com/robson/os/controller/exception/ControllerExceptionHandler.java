package com.robson.os.controller.exception;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.robson.os.exceptions.DataIntegrityViolationException;
import com.robson.os.exceptions.TecnicoNaoEncontrado;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(TecnicoNaoEncontrado.class)
	public ResponseEntity<StandardError> objectNoFhandleEntidadeNaoAchadaound(TecnicoNaoEncontrado ex) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), OffsetDateTime.now(), ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> objectNoFound(DataIntegrityViolationException ex) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), OffsetDateTime.now(), ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> listaDeErrors(MethodArgumentNotValidException ex) {

		ValidationErro error = new ValidationErro(HttpStatus.BAD_REQUEST.value(), OffsetDateTime.now(),
				"Erro na validação dos campos!");

		for (FieldError x : ex.getBindingResult().getFieldErrors()) {
			error.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

}
