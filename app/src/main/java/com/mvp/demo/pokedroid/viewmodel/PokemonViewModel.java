package com.mvp.demo.pokedroid.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.repository.PokemonRepository;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

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
                .subscribe(new Observer<PokemonList>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "Suscribed !");
                    }

                    @Override
                    public void onNext(PokemonList pokemonList) {
                        adapter.addToPokemonList(pokemonList.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Error: " + e.getMessage());
                        readyToLoad = true;
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "Completed !");
                        readyToLoad = true;
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
