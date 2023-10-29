package com.davidperezpardo.superheroes.repository.superhero.mappers;

import com.davidperezpardo.superheroes.domain.superhero.dto.SuperheroDto;
import com.davidperezpardo.superheroes.domain.superhero.model.Superhero;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-29T13:24:10+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 20.0.1 (Oracle Corporation)"
)
public class SuperheroMapperImpl implements SuperheroMapper {

    @Override
    public SuperheroDto superheroToSuperheroDto(Superhero superhero) {
        if ( superhero == null ) {
            return null;
        }

        SuperheroDto superheroDto = new SuperheroDto();

        superheroDto.setNombre( superhero.getName() );
        superheroDto.setFechaCreacion( superhero.getCreatedAt() );
        superheroDto.setFechaModificacion( superhero.getUpdatedAt() );
        superheroDto.setId( superhero.getId() );

        return superheroDto;
    }
}
