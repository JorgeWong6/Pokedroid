package com.mvp.demo.pokedroid.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mvp.demo.pokedroid.di.key.ViewModelKey;
import com.mvp.demo.pokedroid.viewmodel.FactoryViewModel;
import com.mvp.demo.pokedroid.viewmodel.PokemonViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonViewModel.class)
    abstract ViewModel bindPokemonViewModel(PokemonViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
