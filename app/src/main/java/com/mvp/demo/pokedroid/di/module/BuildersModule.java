package com.mvp.demo.pokedroid.di.module;

import com.mvp.demo.pokedroid.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by atempa on 1/20/18.
 */
@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = {FragmentModule.class})
    abstract MainActivity mainActivityInjector();
}