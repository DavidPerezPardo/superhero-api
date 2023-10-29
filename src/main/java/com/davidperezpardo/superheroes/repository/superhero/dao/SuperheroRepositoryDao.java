/**
 * 
 */
package com.davidperezpardo.superheroes.repository.superhero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidperezpardo.superheroes.domain.superhero.model.Superhero;

/**
 * Superhero repository dao.
 * @author David
 *
 */
@Repository
public interface SuperheroRepositoryDao extends JpaRepository<Superhero, Integer> {
	
	/**
	 * Find and return a list of superheroes that contain the parameter in their name.
	 * If the parameter is not present, return all of them.
	 * Do not return those that are eliminated.
	 * @param name {@link String}
	 * @return a superhero list {@link List}
	 */
	public List<Superhero> findByNameContainsIgnoreCaseAndDeletedAtIsNull(String name);
}
