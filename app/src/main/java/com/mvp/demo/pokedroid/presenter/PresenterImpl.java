package com.mvp.demo.pokedroid.presenter;

import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

/**
 * Created by jatempa on 10/23/17.
 */

public class PresenterImpl implements Presenter {
    private PokemonViewModel viewModel;

    public void setViewModel(PokemonViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void fetchData(int offset) {
        if ((viewModel.getOffset() == 0) && (!viewModel.isReadyToLoad())) {
            viewModel.setReadyToLoad(true);
            viewModel.getPokemons(offset);
        }
    }

    @Override
    public void updateData(int visibleItemCount, int totalItemCount, int pastVisibleItems) {
        if (viewModel.isReadyToLoad()) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                viewModel.setReadyToLoad(false);
                viewModel.setOffset(viewModel.getOffset() + PokemonViewModel.MAX);
                viewModel.getPokemons(viewModel.getOffset());
            }
        }
    }
}