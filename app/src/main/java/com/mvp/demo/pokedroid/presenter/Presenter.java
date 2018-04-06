package com.mvp.demo.pokedroid.presenter;

import com.mvp.demo.pokedroid.ui.PokemonAdapter;

/**
 * Created by jatempa on 10/23/17.
 */

public interface Presenter {
    String TAG = "POKEDEX";
    String INFO_MESSAGE = "Llegamos al final";
    int MAX = 20;
    void fetchData(int offset);
    void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems);
}

