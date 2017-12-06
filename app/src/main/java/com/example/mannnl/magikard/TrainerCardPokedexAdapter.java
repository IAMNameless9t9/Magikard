package com.example.mannnl.magikard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class TrainerCardPokedexAdapter extends ArrayAdapter<TrainerCard> {

    private ArrayList<TrainerCard> pokedexList = new ArrayList<>();

    TrainerCardPokedexAdapter(Context context, int textViewResourceId, ArrayList<TrainerCard> cards) {
        super(context, textViewResourceId, cards);
        pokedexList = cards;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_trainer_card, null);

        TextView trainerCardName = v.findViewById(R.id.tvTrainerCardName);
        TextView trainerCardType = v.findViewById(R.id.tvTrainerCardType);
        TextView trainerCardDesc = v.findViewById(R.id.tvTrainerCardDescription);

        trainerCardName.setText(pokedexList.get(position).getmName());
        trainerCardType.setText(pokedexList.get(position).getmType());
        trainerCardDesc.setText(pokedexList.get(position).getmDescription());

        return v;

    }

}
