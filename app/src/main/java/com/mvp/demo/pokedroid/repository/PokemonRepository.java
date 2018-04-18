package com.mvp.demo.pokedroid.repository;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.database.PokemonDao;
import com.mvp.demo.pokedroid.model.Pokemon;
import com.mvp.demo.pokedroid.model.PokemonList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class PokemonRepository {
    private final PokemonService service;
    private final PokemonDao dao;

    @Inject
    public PokemonRepository(PokemonService service, PokemonDao dao) {
        this.service = service;
        this.dao = dao;
    }

    public Flowable<List<Pokemon>> getPokemons(int max, int offset) {
        service.getPokemonList(max, offset)
               .subscribeOn(Schedulers.io())
               .observeOn(Schedulers.newThread())
               .subscribe(new Consumer<PokemonList>() {
                   @Override
                   public void accept(PokemonList pokemonList) throws Exception {
                       ArrayList<Pokemon> pokemons = pokemonList.getResults();
                       for (Pokemon pokemon : pokemons) {
                           dao.insert(pokemon);
                       }
                   }
               });

        return dao.getAll()
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread());
    }
}
