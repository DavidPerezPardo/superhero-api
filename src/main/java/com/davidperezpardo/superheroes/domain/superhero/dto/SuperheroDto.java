package com.davidperezpardo.superheroes.domain.superhero.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Superhero DTO class.
 * 
 * @author David
 *
 */
@ApiModel(description = "Superhero DTO de entrada/salida para las peticiones")
@Getter
@Setter
@ToString
public class SuperheroDto implements Serializable{

	private static final long serialVersionUID = -5995394643833533310L;
	
	@ApiModelProperty(value = "identificador único", accessMode = AccessMode.READ_ONLY)
	private Integer id;
	
	@ApiModelProperty(value = "Nombre del Superheroe", required = true)
	@NotBlank
	@Size(max = 255)
	private String nombre;
	
	@ApiModelProperty(value = "Fecha de creación", accessMode = AccessMode.READ_ONLY)
	@Nullable
    @JsonFormat(pattern="dd-MM-YYYY HH:mm:ss")
	@JsonProperty("fecha_creacion")
	private Date fechaCreacion;
	
	@ApiModelProperty(value = "Fecha de modificación", accessMode = AccessMode.READ_ONLY)
	@Nullable
    @JsonFormat(pattern="dd-MM-YYYY HH:mm:ss")
	@JsonProperty("fecha_modificacion")
	private Date fechaModificacion;
}
