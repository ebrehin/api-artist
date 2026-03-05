package com.repositories;

import com.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    boolean existsByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);

    Optional<Artist> findByNomIgnoreCaseAndPrenomIgnoreCase(String nom, String prenom);
}
