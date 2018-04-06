package com.mvp.demo.pokedroid.presenter;

import com.mvp.demo.pokedroid.ui.PokemonAdapter;

/**
 * Created by jatempa on 10/23/17.
 */

public interface Presenter {
    void fetchData(int offset);
    void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems);
    PokemonAdapter getAdapter();
}

