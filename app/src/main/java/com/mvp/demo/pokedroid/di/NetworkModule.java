package com.mvp.demo.pokedroid.di;

import com.mvp.demo.pokedroid.model.PokeapiService;

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
    private final String BASE_URL = "https://pokeapi.co/api/v2/";

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    PokeapiService provideApiService(Retrofit retrofit) {
        return retrofit.create(PokeapiService.class);
    }
}
