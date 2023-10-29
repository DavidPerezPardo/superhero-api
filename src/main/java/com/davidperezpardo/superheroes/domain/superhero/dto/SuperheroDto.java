/**
 * 
 */
package com.davidperezpardo.superheroes.domain.superhero.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

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

	private Integer id;
	
	private String nombre;
	
	@JsonProperty("fecha_creacion")
	private String fechaCreacion;
	
	@JsonProperty("fecha_modificacion")
	private String fechaModificacion;
	
}
