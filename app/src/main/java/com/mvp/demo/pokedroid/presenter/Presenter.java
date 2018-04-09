package com.mvp.demo.pokedroid.presenter;

import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

/**
 * Created by jatempa on 10/23/17.
 */

public interface Presenter {
    void setViewModel(PokemonViewModel viewModel);
    void fetchData(int offset);
    void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems);
}

