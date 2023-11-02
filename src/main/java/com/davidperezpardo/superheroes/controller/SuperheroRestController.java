package com.davidperezpardo.superheroes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.service.superhero.SuperheroService;

/**
 * Superhero controller to do CRUD methods
 * @author David
 *
 */
@RequestMapping("/api/superheroes")
@RestController
public class SuperheroRestController {
	
	@Autowired
	private SuperheroService superheroService;
	
	@GetMapping(value = "/get-all-superheroes")
	public ResponseEntity<List<SuperheroDto>> getAll(@RequestParam(value = "searchName", required = false, defaultValue = "") @Valid String searchName) throws ServiceException {
		
		var result = superheroService.getAll(searchName);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/get-superhero/{id}")
	public ResponseEntity<SuperheroDto> getById(@PathVariable Integer id) throws ServiceException {
		
		var result = superheroService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update-superhero/{id}")
	public ResponseEntity<SuperheroDto> update(@PathVariable("id") Integer id, @RequestBody @Valid SuperheroDto superheroDto) throws ServiceException {
		
		var updatedSuperhero = superheroService.saveOrUpdate(superheroDto, id);
		return new ResponseEntity<>(updatedSuperhero, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete-superhero/{id}")  
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws ServiceException {
		
		superheroService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(value = "/save-superhero")  
	public ResponseEntity<SuperheroDto> insert(@RequestBody @Valid SuperheroDto superheroDto) throws ServiceException {
		
		var savedSuperhero = superheroService.saveOrUpdate(superheroDto, null);
		return new ResponseEntity<>(savedSuperhero, HttpStatus.CREATED);
	}
}
