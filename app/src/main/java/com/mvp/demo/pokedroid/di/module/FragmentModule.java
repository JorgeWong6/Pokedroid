package com.mvp.demo.pokedroid.di.module;

import com.mvp.demo.pokedroid.ui.PokemonFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract PokemonFragment contributesPokemonFragment();
}
