package com.controllers;

import com.dtos.ArtistDto;
import com.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST exposant les endpoints
 * relatifs aux artistes.
 *
 * <p>Base URL : <b>/api/artistes</b></p>
 */
@RestController
@RequestMapping("/api/artistes")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService service;

	/**
	 * Crée un artiste.
	 */
	@PostMapping
    public ArtistDto create(@RequestBody ArtistDto dto) {
		return service.create(dto);
	}

	/**
	 * Récupère un artiste par id.
	 */
	@GetMapping("/{id}")
    public ArtistDto getById(@PathVariable Long id) {
		return service.getById(id);
	}

	/**
	 * Récupère tous les artistes.
	 */
	@GetMapping
    public List<ArtistDto> getAll() {
		return service.getAll();
	}

	/**
	 * Supprime un artiste.
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
