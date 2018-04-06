package com.mvp.demo.pokedroid.di.component;

import android.app.Application;

import com.mvp.demo.pokedroid.App;
import com.mvp.demo.pokedroid.di.module.AppModule;
import com.mvp.demo.pokedroid.di.module.BuildersModule;
import com.mvp.demo.pokedroid.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by jatempa on 10/23/17.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        BuildersModule.class,
        NetworkModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(App app);
}