package com.mobiledev.homework2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonArrayAdapter extends ArrayAdapter<Pokemon> {


    private static class ViewHolder {
        TextView name, id, weight, height;
        ImageView image;
    }

    public PokemonArrayAdapter(Context context, int resource, List<Pokemon> objects) {
        super(context, resource, objects);
    }

    public void removePokemon(int position) {
        remove(getItem(position));
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.individual_row_pokemon, parent, false);

            TextView nameTextView = (TextView) convertView.findViewById(R.id.row_pokemon_textView);
            TextView idTextView = (TextView) convertView.findViewById(R.id.row_pokemon_id);
            TextView weightTextView = (TextView) convertView.findViewById(R.id.row_pokemon_weight);
            TextView heightTextView = (TextView) convertView.findViewById(R.id.row_pokemon_height);
            ImageView listPokemonImageView = (ImageView) convertView.findViewById(R.id.individual_row_image);

            viewHolder = new ViewHolder();
            viewHolder.name = nameTextView;
            viewHolder.id = idTextView;
            viewHolder.height = heightTextView;
            viewHolder.weight = weightTextView;
            viewHolder.image = listPokemonImageView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Pokemon pokemon = getItem(position);

        viewHolder.name.setText(pokemon.getName());
        viewHolder.id.setText(pokemon.getId());

        Picasso.with(getContext()).load(pokemon.getImageUrl()).fit().centerInside().into(viewHolder.image);

        String weight = getContext().getString(R.string.weight_label, pokemon.getWeight());

        viewHolder.weight.setText(weight);

        String height = getContext().getString(R.string.height_label, pokemon.getHeight());
        viewHolder.height.setText(height);


        return convertView;
    }
}
