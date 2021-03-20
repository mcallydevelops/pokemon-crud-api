package com.mcallydevelops.controller;

import com.mcallydevelops.models.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PokemonControllerTest {

    private JdbcTemplate jdbcTemplate;
    private PokemonController pokemonController;
    @BeforeEach
    void setup() {
        jdbcTemplate = mock(JdbcTemplate.class);
        pokemonController = new PokemonController(jdbcTemplate);
    }

    @Test
    void getPokemonByName() {
        //arrange
        Map<String, Object> queryResult = new HashMap<>();
        queryResult.put("name", "Pikachu");
        when(jdbcTemplate.queryForList("SELECT * FROM POKEMON where name = ?", "Pikachu"))
                .thenReturn(Collections.singletonList(queryResult));
        //act
        Pokemon result = pokemonController.getPokemonByName("Pikachu");

        //assert
        assertEquals("Pikachu", result.getName());
    }
}