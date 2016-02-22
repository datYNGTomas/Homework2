package com.mobiledev.homework2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

public class PokemonASyncTask extends AsyncTask<String, Integer, JSONArray> {

    public static final String TAG = PokemonASyncTask.class.getSimpleName();

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "Pokedex 2000 Lite has been activited");
    }

    @Override
    protected JSONArray doInBackground(String... params) {
        return null;
    }
}
