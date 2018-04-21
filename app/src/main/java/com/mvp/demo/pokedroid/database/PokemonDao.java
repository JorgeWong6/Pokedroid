package com.mvp.demo.pokedroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mvp.demo.pokedroid.model.Pokemon;

import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface PokemonDao {
    @Insert
    void insert(Pokemon pokemon);

    @Query("SELECT COUNT(id) FROM pokemons")
    int getTotal();

    @Query("SELECT * FROM pokemons WHERE name = :name AND lastRefresh > :lastRefreshMax LIMIT 1")
    Pokemon hasPokemon(String name, Date lastRefreshMax);

    @Query("SELECT * FROM pokemons LIMIT :max OFFSET :offset")
    Flowable<List<Pokemon>> getAll(int max, int offset);
}
