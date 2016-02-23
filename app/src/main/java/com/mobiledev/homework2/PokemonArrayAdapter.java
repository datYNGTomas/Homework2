package com.mobiledev.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PokemonArrayAdapter extends ArrayAdapter<Pokemon> {


    private static class ViewHolder {
        TextView name, id, weight, height;
        ImageView image;
    }

    public PokemonArrayAdapter(Context context, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View mArrayView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        ViewHolder viewHolder;
        if (mArrayView == null) {
            mArrayView = inflater.inflate(R.layout.individual_row_pokemon, parent, false);

            TextView nameTextView = (TextView) mArrayView.findViewById(R.id.row_pokemon_textView);
            TextView idTextView = (TextView) mArrayView.findViewById(R.id.row_pokemon_id);
            TextView weightTextView = (TextView) mArrayView.findViewById(R.id.row_pokemon_weight);
            TextView heightTextView = (TextView) mArrayView.findViewById(R.id.row_pokemon_height);
            ImageView listPokemonImageView = (ImageView) mArrayView.findViewById(R.id.individual_row_image);

            viewHolder = new ViewHolder();
            viewHolder.name = nameTextView;
            viewHolder.id = idTextView;
            viewHolder.height = heightTextView;
            viewHolder.weight = weightTextView;
            viewHolder.image = listPokemonImageView;

            mArrayView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) mArrayView.getTag();
        }

        Pokemon pokemon = getItem(position);

        viewHolder.name.setText(pokemon.getName());
        viewHolder.id.setText(pokemon.getId());


        String weight = getContext().getString(R.string.weight_label, pokemon.getWeight());

        viewHolder.weight.setText(weight);

        String height = getContext().getString(R.string.height_label, pokemon.getHeight());
        viewHolder.height.setText(height);


        return mArrayView;
    }
}