package com.mvp.demo.pokedroid.presenter;

import android.util.Log;

import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

import javax.inject.Inject;

/**
 * Created by jatempa on 10/23/17.
 */

public class PresenterImpl implements Presenter {
    public static int offset = 0;
    public static boolean readyToLoad = false;
    private PokemonObserver observer;
    private PokemonViewModel viewModel;

    @Inject
    public PresenterImpl(PokemonObserver observer, PokemonViewModel viewModel) {
        this.observer = observer;
        this.viewModel = viewModel;
    }

    @Override
    public void fetchData(int offset) {
        viewModel.getPokemons(MAX, offset).subscribe(observer);
    }

    @Override
    public void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems) {
        if (readyToLoad) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                Log.i(TAG, INFO_MESSAGE);
                readyToLoad = false;
                offset += MAX;
                fetchData(offset);
            }
        }
    }
}