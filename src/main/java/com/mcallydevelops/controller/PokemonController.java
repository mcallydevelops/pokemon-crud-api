package com.mcallydevelops.controller;

import com.mcallydevelops.models.Pokemon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Pokemon getPokemonByName(@PathVariable String name) {
        Pokemon pokemon = new Pokemon();
        List<Map<String, Object>> pokemonList = jdbcTemplate.queryForList("SELECT * FROM POKEMON where name = ?", name);
        pokemon.setName(pokemonList.get(0).get("name").toString());
        return pokemon;
    }
}
