package com.davidperezpardo.superheroes.service.superhero;

import java.util.List;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;

/**
 * Superhero service interface.
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
	 * @exception ServiceException {link ServiceException}
	 */
	public List<SuperheroDto> getAll(String name) throws ServiceException;
	
	/**
	 * Find a superhero by id.
	 * @param superhero id {@link Integer}
	 * @exception {link ServiceException}
	 * @return superheroDto {@link SuperheroDto}
	 * @exception ServiceException {link ServiceException}
	 */
	public SuperheroDto findById(Integer id) throws ServiceException;
	
	/**
	 * Save or update a superhero.
	 * @param superhero {@link SuperheroDto}
	 * @return superheroDto {link {@link SuperheroDto}
	 * @exception ServiceException {link ServiceException}
	 */
	public SuperheroDto saveOrUpdate(SuperheroDto superheroDto, Integer id) throws ServiceException;
	
	/**
	 * Delete a superhero setting datetime in deleted_at column.
	 * @param superhero id {@link Integer}
	 * @exception ServiceException {link ServiceException}
	 */
	public void delete(Integer id) throws ServiceException;
}
