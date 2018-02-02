package com.example.mannnl.magikard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DeckListLibAddViewActivity extends AppCompatActivity {

    ListView deckListView;
    ArrayList<DeckList> decks = new ArrayList<>();
    private String filename = "deckLists.txt";
    ArrayList<PokemonCard> pokemon = new ArrayList<>();
    ArrayList<TrainerCard> trainers = new ArrayList<>();
    ArrayList<EnergyCard> energy = new ArrayList<>();
    TextView instructions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decklist_lib_add_view);

        deckListView = (ListView) findViewById(R.id.deckListView);
        instructions = (TextView) findViewById(R.id.deckListInstructions);

        //Initialises Button States
        instructions.setText(R.string.deckAddModeHint);

        //
        File file = getBaseContext().getFileStreamPath(filename);
        if(file.exists())
        {
            Log.i("Success", "File found");

        } else {
            Log.i("Error", "File not found");

        }

        loadFromFile(filename, getApplicationContext());

        Intent in = this.getIntent();
        String pokemonName = in.getStringExtra("pokemonName");
        String pokemonHP = in.getStringExtra("pokemonHP");
        String pokemonType = in.getStringExtra("pokemonType");

        pokemon.add(new PokemonCard(pokemonName, pokemonHP, pokemonType));


        //Sets the list view to the array
        DeckListViewAdapter deckListAdapter = new DeckListViewAdapter(this, R.layout.item_decklist, decks);

        deckListView.setAdapter(deckListAdapter);

        //Handles click on items in the list
        deckListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = decks.get(position).getmName();
                String format = decks.get(position).getmFormat();

                    decks.add(new DeckList(name, format, pokemon, trainers, energy, false));

                    Intent intent = new Intent(DeckListLibAddViewActivity.this, DeckListContentViewActivity.class);
                    startActivity(intent);

                }

        });

    }



    public void writeToFile(String text, Context ctx) {
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
            Log.i("save", "Saved Data");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("save", "Save Failed!");
        }
    }

    public void loadFromFile(String fileName, Context ctx){
        decks.clear();
        try {
            FileInputStream fis = ctx.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            BufferedReader in = new BufferedReader(isr);
            String str;
            if((str = in.readLine()) != null) {
                while (str.contains("|")) {
                    String name = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    String format = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    String strDelete = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    if( strDelete.equals("false")) {
                        if (!name.equals("null")) {
                            decks.add(new DeckList(name, format, pokemon, trainers, energy, false));
                        } }
                }
            }
            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
