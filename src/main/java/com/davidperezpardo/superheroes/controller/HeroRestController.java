/**
 * 
 */
package com.davidperezpardo.superheroes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidperezpardo.superheroes.domain.exception.ServiceException;
import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.service.superhero.SuperheroService;

/**
 * @author David
 *
 */
@RequestMapping("/api/superheroes")
@RestController
public class HeroRestController {
	
	@Autowired
	private SuperheroService superheroService;
	
	@GetMapping(value = "/get-all-superheroes")
	public ResponseEntity<List<SuperheroDto>> getAll(@RequestParam(value = "searchName", required = false, defaultValue = "") String searchName) throws ServiceException {
		
		var result = superheroService.getAll(searchName);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get-superhero/{id}")
	public ResponseEntity<SuperheroDto> getById(@PathVariable Integer id) throws ServiceException {
		
		var result = superheroService.findById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PatchMapping(value = "/update-superhero/{id}")
	public ResponseEntity<SuperheroDto> update(@PathVariable Integer id, @RequestBody SuperheroDto superheroDto) throws ServiceException {
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete-superhero/{id}")  
	public ResponseEntity<SuperheroDto> delete(@PathVariable Integer id) throws ServiceException {
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@PostMapping(value = "/save-superhero")  
	public ResponseEntity<SuperheroDto> insert(@RequestBody SuperheroDto superheroDto) throws ServiceException {
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
