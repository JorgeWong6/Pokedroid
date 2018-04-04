package com.mvp.demo.pokedroid.di;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.mvp.demo.pokedroid.model.PokeapiService;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.presenter.PresenterImpl;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by atempa on 1/21/18.
 */
@Module
public class MainActivityModule {
    @Provides
    @Singleton
    Presenter providePresenter(PokemonAdapter adapter, PokeapiService service) {
        return new PresenterImpl(adapter, service);
    }

    @Provides
    PokemonAdapter providePokemonAdapter() {
        return new PokemonAdapter();
    }

    @Provides
    GridLayoutManager provideLayoutManager(Context context) {
        return new GridLayoutManager(context, 3);
    }
}
