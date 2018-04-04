package com.mvp.demo.pokedroid.presenter;

import android.util.Log;

import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by jatempa on 10/23/17.
 */

public class PokemonObserver implements Observer<PokemonList> {
    private PokemonAdapter adapter;

    public PokemonObserver(PokemonAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onSubscribe(Disposable d) {
        Log.d(PresenterImpl.TAG, "Suscribed !");
    }

    @Override
    public void onNext(PokemonList pokemonList) {
        adapter.addToPokemonList(pokemonList.getResults());
    }

    @Override
    public void onError(Throwable e) {
        Log.e(PresenterImpl.TAG, " onFailure: " + e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.d(PresenterImpl.TAG, "Completed !");
        PresenterImpl.readyToLoad = true;
    }
}