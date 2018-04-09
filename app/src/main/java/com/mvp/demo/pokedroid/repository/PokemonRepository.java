package com.mvp.demo.pokedroid.repository;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.model.PokemonList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class PokemonRepository {
    private PokemonService service;

    @Inject
    public PokemonRepository(PokemonService service) {
        this.service = service;
    }

    public Observable<PokemonList> getPokemons(int max, int offset) {
        return service.getPokemonList(max, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
