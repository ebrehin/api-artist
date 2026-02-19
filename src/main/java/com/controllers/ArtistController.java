package com.controllers;

import com.dtos.ArtistDto;
import com.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controleur REST exposant les endpoints
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
	 * Cree un artiste.
	 */
	@PostMapping
    public ArtistDto create(@RequestBody ArtistDto dto) {
		return service.create(dto);
	}

	/**
	 * Recupere un artiste par id.
	 */
	@GetMapping("/{id}")
    public ArtistDto getById(@PathVariable Long id) {
		return service.getById(id);
	}

	/**
	 * Met a jour un artiste.
	 */
	@PutMapping("/{id}")
	public ArtistDto update(@PathVariable Long id, @RequestBody ArtistDto dto) {
		return service.update(id, dto);
	}

	/**
	 * Recupere tous les artistes.
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
