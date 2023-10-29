/**
 * 
 */
package com.davidperezpardo.superheroes.domain.exception;

import com.davidperezpardo.superheroes.domain.constant.ErrorsConstants;

import lombok.Getter;

/**
 * Custom exception.
 * @author David
 *
 */
@Getter
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1571486085052566350L;
	private final int code;
	
	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}
	
	public ServiceException(int code, String message, Throwable throweble) {
		super(message, throweble);
		this.code = code;
	}
	
	public ServiceException(ErrorsConstants error) {
		this(error.getCodeError(), error.getDescriptionError());
	}
}
