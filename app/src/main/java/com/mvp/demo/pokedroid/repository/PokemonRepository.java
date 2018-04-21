package com.mvp.demo.pokedroid.repository;

import android.util.Log;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.database.PokemonDao;
import com.mvp.demo.pokedroid.model.Pokemon;
import com.mvp.demo.pokedroid.model.PokemonList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class PokemonRepository {
    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final PokemonService service;
    private final PokemonDao dao;
    private final Executor executor;

    @Inject
    public PokemonRepository(PokemonService service, PokemonDao dao) {
        this.service = service;
        this.dao = dao;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public Flowable<List<Pokemon>> getPokemons(int max, final int offset) {
        fetchData(max, offset);

        return dao.getAll(max, offset)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread());
    }

    private void fetchData(int max, final int offset) {
        service.getPokemonList(max, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<PokemonList>() {
                    @Override
                    public void accept(PokemonList pokemonList) throws Exception {
                        if (dao.getTotal() == offset) {
                            for (Pokemon pokemon : pokemonList.getResults()) {
                                boolean pokemonExists = (dao.hasPokemon(pokemon.getName(), getMaxRefreshTime(new Date())) != null);

                                if(!pokemonExists) {
                                    pokemon.setLastRefresh(new Date());
                                    dao.insert(pokemon);
                                }
                            }
                        }
                    }
                });
    }

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
