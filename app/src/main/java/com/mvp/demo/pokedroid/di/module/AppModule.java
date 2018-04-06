package com.mvp.demo.pokedroid.di.module;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.mvp.demo.pokedroid.api.PokemonService;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.presenter.PresenterImpl;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

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
    Presenter providePresenter(PokemonAdapter adapter, PokemonService service) {
        return new PresenterImpl(adapter, service);
    }

    @Provides
    @Singleton
    PokemonAdapter providePokemonAdapter() {
        return new PokemonAdapter();
    }

    @Provides
    GridLayoutManager provideLayoutManager(Context context) {
        return new GridLayoutManager(context, 3);
    }
}