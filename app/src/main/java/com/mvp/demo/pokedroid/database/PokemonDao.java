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
    void insertAll(List<Pokemon> pokemons);

    @Query("SELECT COUNT(id) FROM pokemons")
    int getTotal();

    @Query("SELECT * FROM pokemons LIMIT :max OFFSET :offset")
    Flowable<List<Pokemon>> getAll(int max, int offset);
}
