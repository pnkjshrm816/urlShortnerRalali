package com.pankaj.urlshortner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnResponsableEntityException extends RuntimeException {
	public UnResponsableEntityException(String message) {
	    super(message);
	  }
}
