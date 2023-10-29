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
}
