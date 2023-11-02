package com.davidperezpardo.superheroes.domain.constant;

import java.util.Arrays;

import lombok.Getter;

/**
 * Constants to Manage the custom errors.
 * @author David
 *
 */
@Getter
public enum ErrorsConstants {

	ERROR_SUPERHERO(1000, "Se produjo un error inesperado en el servidor, inténtelo más tarde!"),
	ERROR_EMPTY_SUPERHERO(1001, "No se encontró un Superheroe con el id proporcionado");
	
	private int codeError;
	private String descriptionError;
	
	private ErrorsConstants(int codError, String descriptionError) {
		this.codeError = codError;
		this.descriptionError = descriptionError;
	}
	
	public static ErrorsConstants getError(int codeError) {
		return Arrays.stream(ErrorsConstants.values())
				.filter(e -> e.getCodeError() == codeError)
				.findFirst()
				.orElseThrow();
	}
}
