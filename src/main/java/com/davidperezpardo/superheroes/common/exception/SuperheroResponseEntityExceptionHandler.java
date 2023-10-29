/**
 * 
 */
package com.davidperezpardo.superheroes.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;

/**
 * Class to manage the custom exception.
 * @author David
 *
 */
@RestControllerAdvice
public class SuperheroResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handler exception for the serviceException.
	 * @param ex {@link ServiceException}
	 * @return response {@link ResponseEntity}
	 */
	  @ExceptionHandler(ServiceException.class)
	  public final ResponseEntity<ErrorInfo> handleServiceException(ServiceException ex) {
	    
		ErrorInfo exceptionResponse = new ErrorInfo(ex);	    
	    return new ResponseEntity<ErrorInfo>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
