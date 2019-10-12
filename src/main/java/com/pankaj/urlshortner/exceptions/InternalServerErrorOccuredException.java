package com.pankaj.urlshortner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorOccuredException extends RuntimeException {
	public InternalServerErrorOccuredException(String message) {
	    super(message);
	  }
}
