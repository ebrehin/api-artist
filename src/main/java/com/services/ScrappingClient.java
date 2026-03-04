package com.services;

import com.dtos.ArtistDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class ScrappingClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String artistByNomPath;

    public ScrappingClient(
            @Value("${scrapping.api.base-url:http://info-tpsi.univ-brest.fr:10003}") String baseUrl,
            @Value("${scrapping.api.artist-by-nom-path:/api/artistes?nom={nom}}") String artistByNomPath) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
        this.artistByNomPath = artistByNomPath;
    }

    public ArtistDto getArtistByNom(String nom) {
        try {
            String url = baseUrl + artistByNomPath;
            ResponseEntity<ArtistDto> response = restTemplate.getForEntity(url, ArtistDto.class, nom);
            return response.getBody();
        } catch (RestClientException e) {
            return null;
        }
    }
}
