package com.mobiledev.homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    PokemonArrayAdapter mAdapter;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework_activity_main);

        ListView listView = (ListView) findViewById(R.id.homework_main_listview);

        Pokedex pokedex = new Pokedex();

        mAdapter = new PokemonArrayAdapter(this, R.id.homework_main_listview, pokedex.getPokemons());

        listView.setAdapter(mAdapter);

    }


}
