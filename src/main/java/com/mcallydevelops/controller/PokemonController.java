package com.mcallydevelops.controller;

import com.mcallydevelops.exceptions.PokemonNotFoundException;
import com.mcallydevelops.models.Pokemon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {


    private final JdbcTemplate jdbcTemplate;

    public PokemonController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    @GetMapping("/name/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) throws PokemonNotFoundException {
        List<Map<String, Object>> pokemonList = jdbcTemplate.queryForList("SELECT ID, NAME FROM POKEMON WHERE NAME = ?", name);

        if(pokemonList.size() == 0) {
            throw new PokemonNotFoundException();
        }

        String pokemonName = pokemonList.get(0).get("NAME").toString();
        Integer id = (Integer) pokemonList.get(0).get("ID");
        return new Pokemon(id, pokemonName);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "Not Found";
    }
}
