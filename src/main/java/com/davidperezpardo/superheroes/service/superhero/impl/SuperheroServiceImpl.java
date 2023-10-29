/**
 * 
 */
package com.davidperezpardo.superheroes.service.superhero.impl;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidperezpardo.superheroes.domain.constant.ErrorsConstants;
import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.repository.superhero.dao.SuperheroRepositoryDao;
import com.davidperezpardo.superheroes.repository.superhero.mappers.SuperheroMapper;
import com.davidperezpardo.superheroes.service.superhero.SuperheroService;

import lombok.extern.log4j.Log4j2;

/**
 * Superhero service implementation.
 * @author David
 *
 */
@Service
@Log4j2
public class SuperheroServiceImpl implements SuperheroService {

	@Autowired
	private SuperheroRepositoryDao superheroRepositoryDao;
	
	private	static final SuperheroMapper superheroMapper =  Mappers.getMapper(SuperheroMapper.class);
	
	@Override
	public List<SuperheroDto> getAll(String name) throws ServiceException {
		
		try {
			
			return null;

		} catch (Exception e) {
			log.error("ERROR - SuperheroServiceImpl.getAll : ".concat(e.getMessage()));
			throw new ServiceException(ErrorsConstants.ERROR_SUPERHERO.getCodeError(), ErrorsConstants.ERROR_SUPERHERO.getDescriptionError());
		}
	}
}
