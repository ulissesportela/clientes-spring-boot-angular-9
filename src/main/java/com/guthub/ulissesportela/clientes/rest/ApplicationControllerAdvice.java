package com.guthub.ulissesportela.clientes.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.guthub.ulissesportela.clientes.restexception.ApiErrors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErros( MethodArgumentNotValidException e) {
		BindingResult bindingResult = e.getBindingResult();
		List<String> mesages = bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
		return new ApiErrors(mesages);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException e) {
		String messageErro = e.getMessage();
		HttpStatus codigoStadus = e.getStatus();
		ApiErrors apiErrors = new ApiErrors(messageErro);
		return new ResponseEntity(apiErrors, codigoStadus);
	}
}
