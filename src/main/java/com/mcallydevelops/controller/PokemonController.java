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
        List<Map<String, Object>> pokemonList = jdbcTemplate.queryForList("SELECT ID, NAME FROM POKEMON WHERE NAME = ?", name);
        String pokemonName = pokemonList.get(0).get("NAME").toString();
        Integer id = (Integer) pokemonList.get(0).get("ID");
        return new Pokemon(id, pokemonName);
    }
}
