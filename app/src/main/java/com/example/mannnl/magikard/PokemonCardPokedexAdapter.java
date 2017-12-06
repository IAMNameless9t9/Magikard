package com.example.mannnl.magikard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

 class PokemonCardPokedexAdapter extends ArrayAdapter<PokemonCard> {

    private ArrayList<PokemonCard> pokedexList = new ArrayList<>();

    PokemonCardPokedexAdapter(Context context, int textViewResourceId, ArrayList<PokemonCard> cards) {
        super(context, textViewResourceId, cards);
        pokedexList = cards;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_pokemon_card, null);

        TextView pokemonCardName = v.findViewById(R.id.tvPokemonCardName);
        TextView pokemonCardHP = v.findViewById(R.id.tvPokemonCardHP);
        TextView pokemonCardType = v.findViewById(R.id.tvPokemonCardType);

        pokemonCardName.setText(pokedexList.get(position).getmName());
        pokemonCardHP.setText(pokedexList.get(position).getmHitPoints());
        pokemonCardType.setText(pokedexList.get(position).getmType());

        return v;

    }

}
