/**
 * 
 */
package com.davidperezpardo.superheroes.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.davidperezpardo.superheroes.domain.constant.ErrorsConstants;
import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.service.superhero.SuperheroService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Tests for superhero rest controller.
 * @author David
 *
 */
@WebMvcTest(SuperheroRestController.class)
public class SuperheroRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SuperheroService superheroService;
	
	@Test
	void superheroGetAllOK() throws Exception {
	
		SuperheroDto superhero = new SuperheroDto();
		superhero.setId(12);
		superhero.setNombre("Spider man");
		
		List<SuperheroDto> superheroList = new ArrayList<>();
		superheroList.add(superhero);

		Mockito.when(superheroService.getAll(""))
			.thenReturn(superheroList);
		
		mockMvc.perform(get("/api/superheroes/get-all-superheroes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpectAll(
                    jsonPath("$.[0].id").exists(),
                    jsonPath("$.[0].nombre").exists(),
                    jsonPath("$.[0].fecha_creacion").isEmpty()
            );
				
		Mockito.verify(superheroService, Mockito.times(1)).getAll("");
	}
	
	@Test
	void superheroGetAllKO() throws Exception {
	
		Mockito.when(superheroService.getAll(""))
			.thenThrow(ServiceException.class);
		
		mockMvc.perform(get("/api/superheroes/get-all-superheroes")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isInternalServerError())
			.andDo(print());
		
		Mockito.verify(superheroService, Mockito.times(1)).getAll("");
	}
	
	@Test
	void superheroCreateOK() throws Exception {
	
		SuperheroDto superhero = new SuperheroDto();
		superhero.setId(12);
		superhero.setNombre("Spider man");
		superhero.setFechaCreacion(Date.from(Instant.now()));
		
		Mockito.when(superheroService.saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.any()))
			.thenReturn(superhero);
	
		mockMvc.perform(post("/api/superheroes/save-superhero")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(superhero))
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isCreated())
			.andDo(print())
			.andExpectAll(
                    jsonPath("$.id").exists(),
                    jsonPath("$.nombre").exists(),
                    jsonPath("$.fecha_creacion").exists()
            );
		
		Mockito.verify(superheroService, Mockito.times(1)).saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.any());
	}
	
	@Test
	void superheroCreateKO() throws Exception {
	
		SuperheroDto superhero = new SuperheroDto();
		superhero.setNombre("Spider man");
		superhero.setFechaCreacion(Date.from(Instant.now()));
		
		Mockito.when(superheroService.saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.any()))
			.thenThrow(ServiceException.class);
	
		mockMvc.perform(post("/api/superheroes/save-superhero")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(superhero))
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isInternalServerError())
			.andDo(print());
		
		Mockito.verify(superheroService, Mockito.times(1)).saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.any());
	}
	
	@Test
	void superheroGetByIdOK() throws Exception {
	
		SuperheroDto superhero = new SuperheroDto();
		superhero.setId(12);
		superhero.setNombre("Spider man");
		superhero.setFechaCreacion(Date.from(Instant.now()));
		superhero.setFechaModificacion(Date.from(Instant.now()));

		Mockito.when(superheroService.findById(Mockito.anyInt()))
			.thenReturn(superhero);
	
		mockMvc.perform(get("/api/superheroes/get-superhero/1")
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpectAll(
                    jsonPath("$.id").exists(),
                    jsonPath("$.nombre").exists(),
                    jsonPath("$.fecha_creacion").exists(),
                    jsonPath("$.fecha_modificacion").exists()
            );
		
		Mockito.verify(superheroService, Mockito.times(1)).findById(Mockito.anyInt());
	}
	
	@Test
	void superheroGetByIdKo() throws Exception {
	
		SuperheroDto superhero = new SuperheroDto();
		superhero.setId(12);
		superhero.setNombre("Spider man");
		superhero.setFechaCreacion(Date.from(Instant.now()));
		superhero.setFechaModificacion(Date.from(Instant.now()));

		Mockito.when(superheroService.findById(Mockito.anyInt()))
			.thenThrow(new ServiceException(ErrorsConstants.ERROR_EMPTY_SUPERHERO));
	
		mockMvc.perform(get("/api/superheroes/get-superhero/1")
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isNotFound())
			.andDo(print());
		
		Mockito.verify(superheroService, Mockito.times(1)).findById(Mockito.anyInt());
	}
	
	@Test
	void superheroUpdateOK() throws Exception {
	
		SuperheroDto superhero = new SuperheroDto();
		superhero.setId(12);
		superhero.setNombre("Spider man");
		superhero.setFechaCreacion(Date.from(Instant.now()));
		superhero.setFechaModificacion(Date.from(Instant.now()));

		SuperheroDto superheroRequest = new SuperheroDto();
		superheroRequest.setNombre("Spider-man");
		
		Mockito.when(superheroService.saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.anyInt()))
			.thenReturn(superhero);
	
		mockMvc.perform(patch("/api/superheroes/update-superhero/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsBytes(superheroRequest))
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpectAll(
                    jsonPath("$.id").exists(),
                    jsonPath("$.nombre").exists(),
                    jsonPath("$.fecha_creacion").exists(),
                    jsonPath("$.fecha_modificacion").exists()
            );
		
		Mockito.verify(superheroService, Mockito.times(1)).saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.anyInt());
	}
	
	@Test
	void superheroUpdateKo() throws Exception {
	
		SuperheroDto superheroRequest = new SuperheroDto();
		superheroRequest.setNombre("Spider-man");
		
		Mockito.when(superheroService.saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.anyInt()))
			.thenThrow(new ServiceException(ErrorsConstants.ERROR_EMPTY_SUPERHERO));
	
		mockMvc.perform(patch("/api/superheroes/update-superhero/1")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsBytes(superheroRequest))
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isNotFound())
			.andDo(print());

		Mockito.verify(superheroService, Mockito.times(1)).saveOrUpdate(Mockito.any(SuperheroDto.class), Mockito.anyInt());
	}
	
	@Test
	void superheroDeleteOK() throws Exception {
		
		Mockito.doNothing().when(superheroService).delete(Mockito.anyInt());
	
		mockMvc.perform(delete("/api/superheroes/delete-superhero/1")
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isOk())
			.andDo(print());
			
		Mockito.verify(superheroService, Mockito.times(1)).delete(Mockito.anyInt());
	}
	
	@Test
	void superheroDeleteKO() throws Exception {
		
		Mockito.doThrow(new ServiceException(ErrorsConstants.ERROR_EMPTY_SUPERHERO))
			.when(superheroService).delete(Mockito.anyInt());
				
		mockMvc.perform(delete("/api/superheroes/delete-superhero/1")
				.accept(MediaType.APPLICATION_JSON)
				.header("X-API-KEY", "api-key-fija"))
			.andExpect(status().isNotFound())
			.andDo(print());
			
		Mockito.verify(superheroService, Mockito.times(1)).delete(Mockito.anyInt());
	}
	
	@Test
	void superheroDeleteKoUnauthorized() throws Exception {
		
		Mockito.doNothing().when(superheroService).delete(Mockito.anyInt());
	
		mockMvc.perform(delete("/api/superheroes/delete-superhero/1")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isUnauthorized())
			.andDo(print());			
	}
}
