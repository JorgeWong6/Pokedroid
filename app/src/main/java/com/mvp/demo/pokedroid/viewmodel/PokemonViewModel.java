package com.mvp.demo.pokedroid.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mvp.demo.pokedroid.model.Pokemon;
import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.repository.PokemonRepository;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {
    private PokemonRepository repository;
    public static final int MAX = 20;
    private static final String TAG = "POKEDROID";
    private int offset = 0;
    private boolean readyToLoad = false;
    @Inject
    PokemonAdapter adapter;

    @Inject
    public PokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
    }

    public void getPokemons(int offset) {
        Log.d(TAG, "OFFSET " + offset);
        repository.getPokemons(MAX, offset)
                  .subscribe(new Consumer<List<Pokemon>>() {
                      @Override
                      public void accept(List<Pokemon> pokemonList) throws Exception {
                          Log.d(TAG, "SIZE " + pokemonList.size());
                          adapter.addToPokemonList((ArrayList<Pokemon>) pokemonList);
                      }
                  });
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isReadyToLoad() {
        return readyToLoad;
    }

    public void setReadyToLoad(boolean readyToLoad) {
        this.readyToLoad = readyToLoad;
    }
}
