package com.mvp.demo.pokedroid.presenter;

import android.util.Log;

import com.mvp.demo.pokedroid.model.PokeapiService;
import com.mvp.demo.pokedroid.model.PokemonList;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

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
    private PokeapiService service;

    public PresenterImpl(PokemonAdapter adapter, PokeapiService service) {
        this.adapter = adapter;
        this.service = service;
    }

    @Override
    public void fetchData(int offset) {
        Log.i(TAG, " OFFSET " + offset);
        Observable<PokemonList> observable = getService().getPokemonList(MAX, offset);
        observable.subscribeOn(Schedulers.io())
                .cache()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new PokemonObserver(getAdapter()));
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

    @Override
    public PokeapiService getService() {
        return service;
    }
}