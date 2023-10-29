package com.davidperezpardo.superheroes.domain.superhero.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author David
 *
 */
@Setter
@Getter
@ToString
@Entity
public class Superhero implements Serializable {

	private static final long serialVersionUID = 260759202941730209L;

	@Id
	private Integer id;
	
	@Column
	@Max(value = 255)
	private String name;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "updated_at")
	private String updatedAt;
	
	@Column(name = "deleted_at")
	private String deletedAt;
}
