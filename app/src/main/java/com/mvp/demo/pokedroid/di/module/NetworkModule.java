package com.mvp.demo.pokedroid.di.module;

import com.mvp.demo.pokedroid.api.PokemonService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by atempa on 1/21/18.
 */
@Module
public class NetworkModule {
    private final static String BASE_URL = "https://pokeapi.co/api/v2/";

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    PokemonService provideApiService(Retrofit retrofit) {
        return retrofit.create(PokemonService.class);
    }
}
