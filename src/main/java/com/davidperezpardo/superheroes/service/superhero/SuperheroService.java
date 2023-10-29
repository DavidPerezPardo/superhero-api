package com.davidperezpardo.superheroes.service.superhero;

import java.util.List;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;

/**
 * Superhero servicce interface.
 * @author David
 *
 */
public interface SuperheroService {

	/**
	 * Find and return a list of superheroes that contain the parameter in their name.
	 * If the parameter is not present, return all of them.
	 * Do not return those that are eliminated.
	 * @param name {@link String}
	 * @return a superhero list {@link List}
	 */
	public List<SuperheroDto> getAll(String name) throws ServiceException;	
}
