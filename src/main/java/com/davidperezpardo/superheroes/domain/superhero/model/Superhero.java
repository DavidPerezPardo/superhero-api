package com.davidperezpardo.superheroes.domain.superhero.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column(name = "created_at", insertable = false)
	private String createdAt;
	
	@Column(name = "updated_at", insertable = false)
	private String updatedAt;
	
	@Column(name = "deleted_at")
	private String deletedAt;
}
