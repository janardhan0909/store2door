package com.store2door.api.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.store2door.api.exceptions.ExceptionResponse;
import com.store2door.api.service.MailerService;

@ControllerAdvice(basePackages = "com.store2door.api")
@RestController
public class GlobalExceptionHandlerController extends ResponseEntityExceptionHandler {
	@Autowired
	MailerService mailerService;
	@Value("${app.env}")
	private String env;
	
	 @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
    	 mailerService.sendEmailOnException(env, "Exception is Form Store 2 Door	 Backend API", ex);
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	
}
