package com.services;

import com.dtos.ArtistDto;

import java.util.List;

/**
 * Interface définissant les opérations métier
 * relatives aux artistes.
 */
public interface ArtistService {

    /**
     * Crée un nouvel artiste.
     *
     * @param artisteDto données de l'artiste à créer
     * @return artiste créé
     */
    ArtistDto create(ArtistDto artisteDto);

    /**
     * Récupère un artiste par son identifiant.
     *
     * @param id identifiant de l'artiste
     * @return artiste trouvé
     */
    ArtistDto getById(Long id);

    /**
     * Récupère la liste complète des artistes.
     *
     * @return liste des artistes
     */
    List<ArtistDto> getAll();

    /**
     * Supprime un artiste par son identifiant.
     *
     * @param id identifiant de l'artiste
     */
    void delete(Long id);
}
