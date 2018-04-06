package com.mvp.demo.pokedroid.repository;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.model.PokemonList;

import javax.inject.Inject;

import io.reactivex.Observable;

public class PokemonRepository {
    private PokemonService service;

    @Inject
    public PokemonRepository(PokemonService service) {
        this.service = service;
    }

    public Observable<PokemonList> getPokemons(int max, int offset) {
        return service.getPokemonList(max, offset);
    }
}
