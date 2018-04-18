package com.mvp.demo.pokedroid.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.mvp.demo.pokedroid.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "app_demo";

    public abstract PokemonDao getPokemonDao();
}
