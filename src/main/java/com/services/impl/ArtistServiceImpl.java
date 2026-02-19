package com.services.impl;

import com.dtos.ArtistDto;
import com.repositories.ArtistRepository;
import com.services.ArtistService;
import com.mappers.ArtistMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Implémentation des opérations métier pour la gestion des chiens.
 * Cette classe suit le principe de Single Responsibility (SOLID).
 */
@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

	private final ArtistRepository artistRepository;
	private final ArtistMapper artistMapper;

	/**
	 * Constructeur avec injection des dépendances
	 * L'injection par constructeur est préférée à @Autowired car :
	 * - Elle rend les dépendances obligatoires
	 * - Elle facilite les tests unitaires
	 * - Elle permet l'immutabilité
	 */
	public ArtistServiceImpl(ArtistRepository artistRepository, ArtistMapper artistMapper) {
		this.artistRepository = artistRepository;
		this.artistMapper = artistMapper;
	}

	/**
	 * {@inheritDoc}
	 * Cette méthode est transactionnelle par défaut grâce à @Transactional sur la classe
	 */
	@Override
	public ArtistDto create(ArtistDto artistDto) {
		var artist = artistMapper.toEntity(artistDto);
		// Ensure a new entity is created even if the client sent an id
		artist.setId(null);
		var savedArtist = artistRepository.save(artist);
		return artistMapper.toDto(savedArtist);
	}

	/**
	 * {@inheritDoc}
	 * Utilisation de la méthode orElseThrow pour une gestion élégante des cas d'erreur
	 */
	@Override
	@Transactional(readOnly = true)
	public ArtistDto getById(Long id) {
		var artist = artistRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("L'artiste avec l'ID %d n'existe pas", id)));
		return artistMapper.toDto(artist);
	}

	/**
	 * {@inheritDoc}
	 * La méthode deleteById ne lève pas d'exception si l'entité n'existe pas
	 */
	@Override
	public void delete(Long id) {
		artistRepository.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 * Met a jour les champs principaux de l'artiste.
	 */
	@Override
	public ArtistDto update(Long id, ArtistDto artistDto) {
		var artist = artistRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						String.format("L'artiste avec l'ID %d n'existe pas", id)));
		artist.setNom(artistDto.getNom());
		artist.setPrenom(artistDto.getPrenom());
		artist.setAge(artistDto.getAge());
		var savedArtist = artistRepository.save(artist);
		return artistMapper.toDto(savedArtist);
	}

	/**
	 * {@inheritDoc}
	 * Utilisation de l'API Stream pour une transformation fonctionnelle des données
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ArtistDto> getAll() {
		return artistRepository.findAll().stream()
				.map(artistMapper::toDto)
				.toList();
	}
}
