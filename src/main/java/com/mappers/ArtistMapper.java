package com.mappers;

import com.dtos.ArtistDto;
import com.entities.Artist;
import org.springframework.stereotype.Component;

/**
 * Mapper responsable de la conversion entre
 * {@link Artist} et {@link ArtistDto}.
 */
@Component
public class ArtistMapper {

    /**
     * Convertit une entité en DTO.
     *
     * @param artist entité source
     * @return DTO correspondant
     */
    public ArtistDto toDto(Artist artist) {
        if (artist == null) {
            return null;
        }

        return ArtistDto.builder()
                .id(artist.getId())
                .nom(artist.getNom())
                .prenom(artist.getPrenom())
                .age(artist.getAge())
                .build();
    }

    /**
     * Convertit un DTO en entité.
     *
     * @param dto objet source
     * @return entité correspondante
     */
    public Artist toEntity(ArtistDto dto) {
        if (dto == null) {
            return null;
        }

        return Artist.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .age(dto.getAge())
                .build();
    }
}
