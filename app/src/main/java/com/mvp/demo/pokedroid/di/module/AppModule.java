package com.mvp.demo.pokedroid.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.mvp.demo.pokedroid.database.AppDatabase;
import com.mvp.demo.pokedroid.database.PokemonDao;
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
    @Singleton
    Presenter providePresenter() {
        return new PresenterImpl();
    }

    @Provides
    @Singleton
    PokemonAdapter providePokemonAdapter() {
        return new PokemonAdapter();
    }

    // --- DATABASE INJECTION ---
    
    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, AppDatabase.DB_NAME).build();
    }

    @Provides
    @Singleton
    PokemonDao providePokemonDao(AppDatabase database) { return database.getPokemonDao(); }
}