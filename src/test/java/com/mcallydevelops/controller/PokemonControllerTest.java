package com.mcallydevelops.controller;

import com.mcallydevelops.models.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokemonControllerTest {

    private JdbcTemplate jdbcTemplate;
    private PokemonController pokemonController;
    @BeforeEach
    void setup() {
        jdbcTemplate = mock(JdbcTemplate.class);
        pokemonController = new PokemonController(jdbcTemplate);
    }

    @ParameterizedTest
    @MethodSource("getPokemonByName_source")
    void getPokemonByName(String name) {
        //arrange
        Map<String, Object> queryResult = new HashMap<>();
        queryResult.put("name", name);
        when(jdbcTemplate.queryForList("SELECT * FROM POKEMON where name = ?", name))
                .thenReturn(Collections.singletonList(queryResult));
        //act
        Pokemon result = pokemonController.getPokemonByName(name);

        //assert
        verify(jdbcTemplate).queryForList("SELECT * FROM POKEMON where name = ?", name);
        assertEquals(name, result.getName());
    }

    static Stream<Arguments> getPokemonByName_source() {
        return Stream.of(
                Arguments.of("Pikachu"),
                Arguments.of("Bulbasaur"),
                Arguments.of("Charmander"),
                Arguments.of("Squirtle")
        );
    }

}