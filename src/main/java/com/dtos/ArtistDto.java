package com.dtos;

import lombok.*;

/**
 * Data Transfer Object représentant un artiste.
 *
 * <p>Utilisé pour exposer les données vers l’extérieur
 * sans exposer directement l'entité JPA.</p>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {

	private Long id;
	private String nom;
	private String prenom;
	private Integer age;
}
