package com.mobiledev.homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
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

        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
