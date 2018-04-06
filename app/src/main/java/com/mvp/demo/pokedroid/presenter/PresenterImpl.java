package com.mvp.demo.pokedroid.presenter;

import android.util.Log;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;
import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jatempa on 10/23/17.
 */

public class PresenterImpl implements Presenter {
    static final String TAG = "POKEDEX";
    private final int MAX = 20;
    public static int offset = 0;
    public static boolean readyToLoad = false;
    private PokemonAdapter adapter;
    private PokemonObserver observer;
    private PokemonViewModel viewModel;

    @Inject
    public PresenterImpl(PokemonAdapter adapter, PokemonObserver observer, PokemonViewModel viewModel) {
        this.adapter = adapter;
        this.observer = observer;
        this.viewModel = viewModel;
    }

    @Override
    public void fetchData(int offset) {
        viewModel.getPokemons(offset).subscribe(observer);
    }

    @Override
    public void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems) {
        if (readyToLoad) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                Log.i(TAG, " Llegamos al final.");
                readyToLoad = false;
                offset += MAX;
                fetchData(offset);
            }
        }
    }

    @Override
    public PokemonAdapter getAdapter() {
        return adapter;
    }
}