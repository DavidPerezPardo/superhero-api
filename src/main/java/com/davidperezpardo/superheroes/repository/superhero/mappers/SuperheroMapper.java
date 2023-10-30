package com.davidperezpardo.superheroes.repository.superhero.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.domain.superhero.model.Superhero;

/**
 * Mapper interface for Superhero entity.
 * 
 * @author David
 *
 */
@Mapper
public interface SuperheroMapper {

	@Mapping(source = "name", target = "nombre")
	@Mapping(source = "createdAt", target = "fechaCreacion")
	@Mapping(source = "updatedAt", target = "fechaModificacion")
	SuperheroDto superheroToSuperheroDto(Superhero superhero);
	
	@Mapping(source = "nombre", target = "name")
	@Mapping(source = "fechaCreacion", target = "createdAt")
	@Mapping(source = "fechaModificacion", target = "updatedAt")
	@Mapping(target = "deletedAt", ignore = true)
	Superhero superheroDtoToSuperhero(SuperheroDto superheroDto);
}
