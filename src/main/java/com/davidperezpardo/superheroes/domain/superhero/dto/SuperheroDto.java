/**
 * 
 */
package com.davidperezpardo.superheroes.domain.superhero.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Superhero DTO class.
 * 
 * @author David
 *
 */
@Getter
@Setter
@ToString
public class SuperheroDto implements Serializable{

	private static final long serialVersionUID = -5995394643833533310L;
    @JsonInclude(Include.NON_NULL)
	private Integer id;
	private String nombre;
	private String fechaCreacion;
	private String fechaModificacion;
	
}
