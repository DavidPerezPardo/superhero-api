package com.davidperezpardo.superheroes.service.superhero.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.davidperezpardo.superheroes.domain.constant.ErrorsConstants;
import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.domain.superhero.model.Superhero;
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
	
	@Autowired
	private EntityManager entityManager;
	
	private	static final SuperheroMapper SUPERHERO_MAPPER =  Mappers.getMapper(SuperheroMapper.class);
	
	@Override
	public List<SuperheroDto> getAll(String name) throws ServiceException {
		
		try {
			
			List<SuperheroDto> superHeroDtoList = new ArrayList<>();
			var result = superheroRepositoryDao.findByNameContainsIgnoreCaseAndDeletedAtIsNull(name);
			
			result.forEach(superhero -> {
				superHeroDtoList.add(SUPERHERO_MAPPER.superheroToSuperheroDto(superhero));
			});
			
			return superHeroDtoList;

		} catch (Exception e) {
			log.error("ERROR - SuperheroServiceImpl.getAll : ".concat(e.toString()));
			throw new ServiceException(ErrorsConstants.ERROR_SUPERHERO.getCodeError(), ErrorsConstants.ERROR_SUPERHERO.getDescriptionError());
		}
	}

	@Override
	public SuperheroDto findById(Integer id) throws ServiceException {

		var foundedSuperhero = superheroRepositoryDao.findByIdAndDeletedAtIsNull(id);

		if(foundedSuperhero.isEmpty()) {
			
			log.warn("ERROR - SuperheroServiceImpl.findById : "
					.concat(ErrorsConstants.ERROR_EMPTY_SUPERHERO.getDescriptionError()
					.concat(":".concat(id.toString()))));
			throw new ServiceException(ErrorsConstants.ERROR_EMPTY_SUPERHERO.getCodeError(),
					ErrorsConstants.ERROR_EMPTY_SUPERHERO.getDescriptionError());
			
		}
		return SUPERHERO_MAPPER.superheroToSuperheroDto(foundedSuperhero.get());
	}
	
	@Transactional(readOnly = true)
	@Override
	public SuperheroDto saveOrUpdate(SuperheroDto superheroDto, Integer id) throws ServiceException {
				
		Superhero savedOrUpdatedSuperhero = new Superhero();
		
		if(null != id) {
			// To update
			superheroDto.setId(id);
			// Throw exception if not exists
			findById(id);
		}
		// Create or update
		savedOrUpdatedSuperhero = superheroRepositoryDao.saveAndFlush(SUPERHERO_MAPPER.superheroDtoToSuperhero(superheroDto));
		entityManager.refresh(savedOrUpdatedSuperhero);
		
		return SUPERHERO_MAPPER.superheroToSuperheroDto(savedOrUpdatedSuperhero);			
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		
		findById(id);
		superheroRepositoryDao.softDeleteById(id);		
	}
}
