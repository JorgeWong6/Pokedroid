package com.mvp.demo.pokedroid.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.repository.PokemonRepository;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private PokemonRepository repository;

    @Inject
    public PokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
    }

    public Observable<PokemonList> getPokemons(int offset) {
        return repository.getPokemons(offset)
                         .subscribeOn(Schedulers.io())
                         .observeOn(AndroidSchedulers.mainThread());
    }
}
