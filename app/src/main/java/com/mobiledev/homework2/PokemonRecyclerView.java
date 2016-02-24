package com.mobiledev.homework2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PokemonRecyclerView extends AppCompatActivity implements PokemonRecyclerViewAdapter.OnPokemonRowClickListener {
    public static final int ID_POKEMON = 0;
    private static final String TAG = "Pokemon";
    private RecyclerView RecyclerView;
    private Pokedex Pokedex;
    private PokemonRecyclerViewAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedPokemonState) {
        super.onCreate(savedPokemonState);
        setContentView(R.layout.activity_pokemon_recycler_view);

        RecyclerView = (RecyclerView) findViewById(R.id.activity_pokemon_recyclerview);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Pokedex = new Pokedex();
        Adapter = new PokemonRecyclerViewAdapter(Pokedex.getPokemons(), this);
        RecyclerView.setAdapter(Adapter);

        //final String url = "http://pokeapi.co/api/v2/pokemon/" + ID_POKEMON;
        //new PokemonASyncTask().execute(url);

        RecyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    @Override
    public void onPokemonRowClick(Pokemon pokemon) {
        Intent intent = new Intent(PokemonRecyclerView.this, PokemonDetailView.class);
        intent.putExtra(PokemonDetailView.ARG_POKEMON, pokemon);
        this.startActivity(intent);
    }
}