/**
 * 
 */
package com.davidperezpardo.superheroes.service.superhero;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
}
