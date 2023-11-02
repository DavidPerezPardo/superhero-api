/**
 * 
 */
package com.davidperezpardo.superheroes.service.superhero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.domain.superhero.model.Superhero;
import com.davidperezpardo.superheroes.repository.superhero.dao.SuperheroRepositoryDao;
import com.davidperezpardo.superheroes.service.superhero.impl.SuperheroServiceImpl;

/**
 * Class to test the Superhero service implementation methods.
 * @author David
 *
 */
@SpringBootTest(classes = SuperheroServiceImpl.class)
public class SuperheroServiceTest {

	@Autowired
	private SuperheroService superheroService;
	
	@MockBean
	private EntityManager entityManager;
	
	@MockBean
	private SuperheroRepositoryDao superheroRepository;
	
	/**
	 * Test when the repository method return a SuperheroDto list OK.
	 * @throws ServiceException
	 */
	@Test
	void getAllOk() throws ServiceException {
		
		Superhero superhero = new Superhero();
		superhero.setName("Lobezno");
		
		List<Superhero> superheroList = new ArrayList<>();
		superheroList.add(superhero);
		
		Mockito.when(superheroRepository.findByNameContainsIgnoreCaseAndDeletedAtIsNull(""))
			.thenReturn(superheroList);
		
		var result = superheroService.getAll("");
		
		assertEquals(result.get(0).getClass(), SuperheroDto.class);
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByNameContainsIgnoreCaseAndDeletedAtIsNull(Mockito.anyString());
	}
	
	/**
	 * Test when the repository method return an exception.
	 * @throws ServiceException
	 */
	@Test
	void getAllKoException() {

		Mockito.when(superheroRepository.findByNameContainsIgnoreCaseAndDeletedAtIsNull(""))
			.thenThrow(NullPointerException.class);
		
		assertThrows(ServiceException.class, () -> {
			superheroService.getAll("");
		});
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByNameContainsIgnoreCaseAndDeletedAtIsNull(Mockito.anyString());
	}
	
	@Test
	void findByIdOk() throws ServiceException {
		
		Superhero superhero = new Superhero();
		superhero.setId(12);
		superhero.setName("Lobezno");
		superhero.setCreatedAt(Date.from(Instant.now()));
		superhero.setUpdatedAt(Date.from(Instant.now()));
		Optional<Superhero> superheroOptional = Optional.of(superhero);
		
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(superheroOptional);
		
		var result = superheroService.findById(1);
		
		assertEquals(result.getClass(), SuperheroDto.class);
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.anyInt());
	}
	
	@Test
	void findByIdKoException() throws ServiceException {
		
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(Optional.empty());

		assertThrows(ServiceException.class, () -> {
			superheroService.findById(1);
		});
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.anyInt());
	}
	
	@Test
	void deleteOk() throws ServiceException {
		
		Superhero superhero = new Superhero();
		superhero.setId(12);
		superhero.setName("Lobezno");
		superhero.setCreatedAt(Date.from(Instant.now()));
		superhero.setUpdatedAt(Date.from(Instant.now()));
		Optional<Superhero> superheroOptional = Optional.of(superhero);
		
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(superheroOptional);
		
		Mockito.doNothing().when(superheroRepository).softDeleteById(Mockito.anyInt());
		
		superheroService.delete(1);
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.anyInt());
		Mockito.verify(superheroRepository, Mockito.times(1)).softDeleteById(Mockito.anyInt());
	}
	
	@Test
	void deleteKoException() throws ServiceException {
			
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(Optional.empty());
				
		assertThrows(ServiceException.class, () -> {
			superheroService.delete(1);
		});
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.anyInt());
	}
	
	@Test
	void updateOk() throws ServiceException {
		
		Superhero superhero = new Superhero();
		superhero.setName("Venom");
		superhero.setCreatedAt(Date.from(Instant.now()));
		superhero.setUpdatedAt(Date.from(Instant.now()));
		Optional<Superhero> superheroOptional = Optional.of(superhero);
		
		SuperheroDto superheroDto = new SuperheroDto();
		superheroDto.setNombre("Venom");
		
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(superheroOptional);
		
		Mockito.when(superheroRepository.saveAndFlush(Mockito.any(Superhero.class)))
			.thenReturn(superhero);
		
		var result = superheroService.saveOrUpdate(superheroDto, 1);
		
		assertEquals(result.getClass(), SuperheroDto.class);
		
		Mockito.verify(superheroRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.anyInt());
		Mockito.verify(superheroRepository, Mockito.times(1)).saveAndFlush(Mockito.any(Superhero.class));
	}
	
	@Test
	void saveOk() throws ServiceException {
		
		Superhero superhero = new Superhero();
		superhero.setName("Venom");
		superhero.setCreatedAt(Date.from(Instant.now()));
		Optional<Superhero> superheroOptional = Optional.of(superhero);
		
		SuperheroDto superheroDto = new SuperheroDto();
		superheroDto.setNombre("Venom");
		
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(superheroOptional);
		
		Mockito.when(superheroRepository.saveAndFlush(Mockito.any(Superhero.class)))
			.thenReturn(superhero);
		
		var result = superheroService.saveOrUpdate(superheroDto, null);
		
		assertEquals(result.getClass(), SuperheroDto.class);
		
		Mockito.verify(superheroRepository, Mockito.times(1)).saveAndFlush(Mockito.any(Superhero.class));
	}
	
	@Test
	void saveOrCreateKoException() throws ServiceException {
				
		SuperheroDto superheroDto = new SuperheroDto();
		superheroDto.setNombre("Venom");
		
		Mockito.when(superheroRepository.findByIdAndDeletedAtIsNull(Mockito.anyInt()))
			.thenReturn(Optional.empty());
		
		assertThrows(ServiceException.class, () ->{
			superheroService.saveOrUpdate(superheroDto, 2);
		});
				
		Mockito.verify(superheroRepository, Mockito.times(1)).findByIdAndDeletedAtIsNull(Mockito.anyInt());
	}
}
