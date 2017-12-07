package com.example.mannnl.magikard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class EnergyCardPokedexAdapter extends ArrayAdapter<EnergyCard> {

    private ArrayList<EnergyCard> pokedexList = new ArrayList<>();

    EnergyCardPokedexAdapter(Context context, int textViewResourceId, ArrayList<EnergyCard> cards) {
        super(context, textViewResourceId, cards);
        pokedexList = cards;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_energy_card, null);

        TextView energyCardName = v.findViewById(R.id.tvEnergyCardName);
        TextView energyCardDesc = v.findViewById(R.id.tvEnergyCardDesc);

        energyCardName.setText(pokedexList.get(position).getmName());
        energyCardDesc.setText(pokedexList.get(position).getmDesc());

        return v;

    }

}
