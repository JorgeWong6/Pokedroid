package com.mvp.demo.pokedroid.di;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.mvp.demo.pokedroid.model.PokeapiService;
import com.mvp.demo.pokedroid.presenter.Presenter;
import com.mvp.demo.pokedroid.presenter.PresenterImpl;
import com.mvp.demo.pokedroid.ui.PokemonAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jatempa on 10/23/17.
 */
@Module
public class AppModule {
    private final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Presenter providePresenter() {
        return new PresenterImpl(providePokemonAdapter(), provideApiService());
    }

    @Provides
    @Singleton
    PokemonAdapter providePokemonAdapter() {
        return new PokemonAdapter();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PokeapiService provideApiService() {
        return provideRetrofit().create(PokeapiService.class);
    }
}