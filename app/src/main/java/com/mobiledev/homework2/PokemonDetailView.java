package com.mobiledev.homework2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDetailView extends AppCompatActivity implements PokemonASyncTask.PokemonASyncTaskListener {

    public final String TAG = PokemonDetailView.class.getSimpleName();
    public static final String ARG_POKEMON = "ArgPokemon";
    private Pokemon mCurrentPokemon;
    private ProgressBar progressBar;
    private PokemonASyncTask mAsyncTask;
    public TextView mHPTextView, mAttackTextView, mDefenseTextView, mSpeedTextview, mSpecialAttackTextView, mSpecialDefenseTextView, mBaseExperienceTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail_view);
        mCurrentPokemon = getIntent().getParcelableExtra(ARG_POKEMON);

        TextView nameTextView = (TextView) findViewById(R.id.activity_detail_pokemon_name_toolbar);
        TextView heightTextView = (TextView) findViewById(R.id.activity_height_number_detail);
        TextView weightTextView = (TextView) findViewById(R.id.activity_weight_number_detail);
        TextView mIDnumberTextView = (TextView) findViewById(R.id.activity_ID_number_detail);
        ImageView pokemonImageView = (ImageView) findViewById(R.id.activity_detail_imageview);

        mHPTextView = (TextView) findViewById(R.id.activity_detail_HP);
        mAttackTextView = (TextView) findViewById(R.id.activity_detail_attack);
        mDefenseTextView = (TextView) findViewById(R.id.activity_detail_defense);
        mSpeedTextview = (TextView) findViewById(R.id.activity_detail_speed);
        mSpecialAttackTextView = (TextView) findViewById(R.id.activity_detail_special_attack);
        mSpecialDefenseTextView = (TextView) findViewById(R.id.activity_detail_special_defense);
        mBaseExperienceTextView = (TextView) findViewById(R.id.activity_detail_base_experience);

        Picasso.with(this).load(mCurrentPokemon.getImageUrl()).fit().centerInside().into(pokemonImageView);

        nameTextView.setText(getString(R.string.corny_title_toolbar, mCurrentPokemon.getName()));
        mIDnumberTextView.setText(getString(R.string.ID_label, mCurrentPokemon.getId()));
        heightTextView.setText(getString(R.string.height_label, mCurrentPokemon.getHeight()));
        weightTextView.setText(getString(R.string.weight_label, mCurrentPokemon.getWeight()));

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Snackbar.make(pokemonImageView, R.string.detail_view_failed_connection_snackbar, Snackbar.LENGTH_LONG).show();
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            Snackbar.make(pokemonImageView, getString(R.string.detail_view_approved_connection_snackbar), Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent();
        intent.putExtra(ARG_POKEMON, mCurrentPokemon);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        //ugh, almost there!
        if (mCurrentPokemon.getmHP() != null) {
            mHPTextView.setText(getString(R.string.detail_view_HP_setText) + mCurrentPokemon.getmHP());
            mAttackTextView.setText(getString(R.string.detail_view_attack_setText) + mCurrentPokemon.getmAttack());
            mDefenseTextView.setText(getString(R.string.detail_view_defense_setText) + mCurrentPokemon.getmDefense());
            mSpeedTextview.setText(getString(R.string.detail_view_speed_setText) + mCurrentPokemon.getmSpeed());
            mSpecialAttackTextView.setText(getString(R.string.detail_view_specialAttack_setText) + mCurrentPokemon.getmSpecialAttack());
            mSpecialDefenseTextView.setText(getString(R.string.detail_view_specialDefense_setText) + mCurrentPokemon.getmSpecialDefense());
            mBaseExperienceTextView.setText(getString(R.string.detail_view_baseExperience_setText) + mCurrentPokemon.getmBaseExperience());
        } else {
            mAsyncTask = new PokemonASyncTask(this);
            mAsyncTask.execute(mCurrentPokemon.getId());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAsyncTask != null && !mAsyncTask.isCancelled())
            mAsyncTask.cancel(true);
    }

    @Override
    public void onCompleteNetworkTask(JSONObject jsonObject) {
        try {
            mCurrentPokemon.setmWeight(jsonObject.get("weight").toString());
            mCurrentPokemon.setmHeight(jsonObject.get("height").toString());
            mCurrentPokemon.setmBaseExperience(jsonObject.get("base_experience").toString());

            JSONArray arrayStats = jsonObject.getJSONArray("stats");

            mCurrentPokemon.setmSpeed(((JSONObject) arrayStats.get(0)).getString("base_stat"));
            mCurrentPokemon.setmSpecialDefense(((JSONObject) arrayStats.get(1)).getString("base_stat"));
            mCurrentPokemon.setmSpecialAttack(((JSONObject) arrayStats.get(2)).getString("base_stat"));
            mCurrentPokemon.setmDefense(((JSONObject) arrayStats.get(3)).getString("base_stat"));
            mCurrentPokemon.setmAttack(((JSONObject) arrayStats.get(4)).getString("base_stat"));
            mCurrentPokemon.setmHP(((JSONObject) arrayStats.get(5)).getString("base_stat"));

        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage());
        }

        mHPTextView.setText(getString(R.string.detail_view_HP_setText) + mCurrentPokemon.getmHP());
        mAttackTextView.setText(getString(R.string.detail_view_attack_setText) + mCurrentPokemon.getmAttack());
        mDefenseTextView.setText(getString(R.string.detail_view_defense_setText) + mCurrentPokemon.getmDefense());
        mSpeedTextview.setText(getString(R.string.detail_view_speed_setText) + mCurrentPokemon.getmSpeed());
        mSpecialAttackTextView.setText(getString(R.string.detail_view_specialAttack_setText) + mCurrentPokemon.getmSpecialAttack());
        mSpecialDefenseTextView.setText(getString(R.string.detail_view_specialDefense_setText) + mCurrentPokemon.getmSpecialDefense());
        mBaseExperienceTextView.setText(getString(R.string.detail_view_baseExperience_setText) + mCurrentPokemon.getmBaseExperience());
        progressBar.setVisibility(View.INVISIBLE);
    }
}