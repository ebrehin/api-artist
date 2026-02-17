package com.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entité représentant un artiste en base de données.
 *
 * <p>Un artiste est défini par :</p>
 * <ul>
 *     <li>Un identifiant unique généré automatiquement</li>
 *     <li>Un nom</li>
 *     <li>Un prénom</li>
 *     <li>Un âge</li>
 * </ul>
 *
 * <p>Cette entité est mappée à la table <b>artistes</b>.</p>
 *
 * @author
 */
@Entity
@Table(name = "artistes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

	/**
	 * Identifiant unique de l'artiste.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nom de famille de l'artiste.
	 */
	@Column(nullable = false)
	private String nom;

	/**
	 * Prénom de l'artiste.
	 */
	@Column(nullable = false)
	private String prenom;

	/**
	 * Âge de l'artiste.
	 */
	@Column(nullable = false)
	private Integer age;
}
