package com.mcallydevelops.controller;

import com.mcallydevelops.models.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokemonControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getPokemonByName() {
        ResponseEntity<Pokemon> result = restTemplate.getForEntity("http://localhost:" + port + "/api/v1/pokemon/name/Pikachu", Pokemon.class);
        Pokemon actual = result.getBody();
        assertEquals("Pikachu", actual.getName());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void getPokemonByNameWherePokemonNotFound() {
        ResponseEntity response = restTemplate.exchange("http://localhost:" + port + "/api/v1/pokemon/name/Bob", HttpMethod.GET, HttpEntity.EMPTY, String.class);
        HttpStatus httpStatusCode = response.getStatusCode();
        assertEquals(HttpStatus.NOT_FOUND, httpStatusCode);
        assertEquals("Not Found", response.getBody());
    }

}
