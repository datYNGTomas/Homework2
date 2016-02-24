package com.mobiledev.homework2;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PokemonASyncTask extends AsyncTask<String, Integer, JSONObject> {

    public static final String TAG = PokemonASyncTask.class.getSimpleName();

    public PokemonASyncTaskListener mListener;
    private Pokemon mCurrentPokemon;
    private View view;

    public PokemonASyncTask(PokemonASyncTaskListener listener) {
        super();
        mListener = listener;
    }

    public interface PokemonASyncTaskListener {
        void onCompleteNetworkTask(JSONObject jsonObject);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "PokemonASyncTask has started");
    }


    @Override
    protected JSONObject doInBackground(String... params) {

        StringBuilder responseBuilder = new StringBuilder();
        JSONObject jsonObject = null;
        HttpURLConnection urlConnection;
        String pokemonID = params[0];
        if (params.length == 0) {
            return null;
        }
        try {
            URL url = new URL("http://pokeapi.co/api/v2/pokemon/" + pokemonID + "/");
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStreamReader inputStream = new InputStreamReader(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);

                if (isCancelled()) {
                    return null;
                }
            }
            jsonObject = new JSONObject(responseBuilder.toString());

            if (isCancelled()) {
                return null;
            }
        } catch (IOException | JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        return jsonObject;

    }


    @Override
    protected void onCancelled(JSONObject jsonObject) {
        super.onCancelled(jsonObject);
        Log.d(TAG, "PokemonASyncTask has been cancelled");
        // cancel something else
    }

//    public void setStats(JSONObject jsonObject) {
//        try {
//            mCurrentPokemon.setmWeight(jsonObject.get("weight").toString());
//            mCurrentPokemon.setmHeight(jsonObject.get("height").toString());
//            mCurrentPokemon.setmBaseExperience(jsonObject.get("base_experience").toString());
//
//            JSONArray arrayStats = jsonObject.getJSONArray("stats");
//
//            mCurrentPokemon.setmSpeed(((JSONObject) arrayStats.get(0)).getString("base_stat"));
//            mCurrentPokemon.setmSpecialDefense(((JSONObject) arrayStats.get(1)).getString("base_stat"));
//            mCurrentPokemon.setmSpecialAttack(((JSONObject) arrayStats.get(2)).getString("base_stat"));
//            mCurrentPokemon.setmDefense(((JSONObject) arrayStats.get(3)).getString("base_stat"));
//            mCurrentPokemon.setmAttack(((JSONObject) arrayStats.get(4)).getString("base_stat"));
//            mCurrentPokemon.setmHP(((JSONObject) arrayStats.get(5)).getString("base_stat"));
//
//        } catch (JSONException e) {
//            Log.e(TAG, e.getLocalizedMessage());
//        }
//    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        if (jsonObject == null) {
            Log.e(TAG, "Resulting JSON is null");
        } else {
            mListener.onCompleteNetworkTask(jsonObject);
        }
    }


}