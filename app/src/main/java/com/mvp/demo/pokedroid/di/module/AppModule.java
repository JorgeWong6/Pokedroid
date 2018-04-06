package com.mvp.demo.pokedroid.di.module;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.presenter.PokemonObserver;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.presenter.PresenterImpl;
import com.mvp.demo.pokedroid.repository.PokemonRepository;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;
import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jatempa on 10/23/17.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Presenter providePresenter(PokemonAdapter adapter, PokemonObserver observer, PokemonViewModel viewModel) {
        return new PresenterImpl(adapter, observer, viewModel);
    }

    @Provides
    @Singleton
    PokemonRepository provideRepository(PokemonService service) {
        return new PokemonRepository(service);
    }

    @Provides
    @Singleton
    PokemonAdapter providePokemonAdapter() {
        return new PokemonAdapter();
    }

    @Provides
    @Singleton
    PokemonObserver providePokemonObserver(PokemonAdapter adapter) {
        return new PokemonObserver(adapter);
    }

    @Provides
    GridLayoutManager provideLayoutManager(Context context) {
        return new GridLayoutManager(context, 3);
    }
}