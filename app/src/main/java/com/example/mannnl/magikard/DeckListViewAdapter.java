package com.example.mannnl.magikard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class DeckListViewAdapter extends ArrayAdapter<DeckList> {

    private ArrayList<DeckList> deckList = new ArrayList<>();

    DeckListViewAdapter(Context context, int textViewResourceId, ArrayList<DeckList> decks) {
        super(context, textViewResourceId, decks);
        deckList = decks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_decklist, null);

        TextView deckListName = v.findViewById(R.id.tvDeckListName);
        TextView deckListFormat = v.findViewById(R.id.tvDeckListFormat);

        deckListName.setText(deckList.get(position).getmName());
        deckListFormat.setText(deckList.get(position).getmFormat());

        return v;

    }

}
