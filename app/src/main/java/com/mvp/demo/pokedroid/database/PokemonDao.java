package com.mvp.demo.pokedroid.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.mvp.demo.pokedroid.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PokemonDao {
    @Insert
    void insertAll(ArrayList<Pokemon> pokemons);

    @Query("SELECT * FROM pokemons")
    List<Pokemon> getAll();
}
