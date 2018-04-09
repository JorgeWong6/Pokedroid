package com.mvp.demo.pokedroid.di.module;

import android.content.Context;

import com.mvp.demo.pokedroid.App;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.presenter.PresenterImpl;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jatempa on 10/23/17.
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    Presenter providePresenter() {
        return new PresenterImpl();
    }

    @Provides
    @Singleton
    PokemonAdapter providePokemonAdapter() {
        return new PokemonAdapter();
    }
}