package com.lawencon.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.community.dto.ErrorRes;
import com.lawencon.community.exception.InvalidLoginException;
import com.lawencon.util.VerificationCodeUtil;
import com.lawencon.util.VerificationCodeUtil.InvalidVerificationCodeException;

@ControllerAdvice
public class ErrorHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleDtoValidation(MethodArgumentNotValidException e) {
		List<String> messages = new ArrayList<>();

		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			messages.add(fieldError.getDefaultMessage());
		}
		ErrorRes<List<String>> errorRes = new ErrorRes<>();
		errorRes.setMessage(messages);

		return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<?> handleFailLogin(InvalidLoginException e) {
		ErrorRes<String> response = new ErrorRes<>();
		response.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(VerificationCodeUtil.InvalidVerificationCodeException.class)
	public ResponseEntity<?> handleInvalidVerificationCode(InvalidVerificationCodeException e){
		ErrorRes<String> response = new ErrorRes<>();
		response.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedException e) {
		ErrorRes<String> response = new ErrorRes<>();
		response.setMessage(NestedExceptionUtils.getMostSpecificCause(e).getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
	}

}
