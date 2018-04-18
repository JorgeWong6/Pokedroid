package com.mvp.demo.pokedroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mvp.demo.pokedroid.model.Pokemon;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PokemonDao {
    @Insert
    void insert(Pokemon pokemon);

    @Query("SELECT * FROM pokemons")
    Flowable<List<Pokemon>> getAll();
}
