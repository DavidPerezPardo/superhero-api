/**
 * 
 */
package com.davidperezpardo.superheroes.repository.superhero.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	/**
	 * Find and return a superhero by id with deleted_at field null
	 * (soft delete).
	 * @param id {@link Integer}
	 * @return a superhero {@link Superhero}
	 */
	public Optional<Superhero> findByIdAndDeletedAtIsNull(Integer id);
	
	/**
	 * Soft delete a superhero setting a current timestamp in DELETED_AT column.
	 * @param superhero id {link Integer}
	 */
	@Transactional
	@Modifying
	@Query(value = "UPDATE SUPERHERO SET DELETED_AT = CURRENT_TIMESTAMP WHERE ID = :id", nativeQuery = true)
	void softDeleteById(@Param("id") Integer id);
}
