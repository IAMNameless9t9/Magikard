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

public class DeckListContentViewActivity extends AppCompatActivity {

    ListView deckListView;
    DeckList deck = new DeckList();
    ArrayList<PokemonCard> pokemon = new ArrayList<>();
    ArrayList<TrainerCard> trainers = new ArrayList<>();
    ArrayList<EnergyCard> energy = new ArrayList<>();
    private String filename;
    private String saveData;
    Button deckListViewMode;
    Button deckListEditMode;
    Button deckListDeleteMode;
    Boolean ViewMode = true;
    Boolean EditMode = false; //TODO: when updating the name, make sure to change the deck filename
    Boolean DeleteMode = false;
    TextView deckName;
    TextView deckFormat;
    TextView instructions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decklist_lib_view);


        deckListView = (ListView) findViewById(R.id.deckListView);
        deckListViewMode = (Button) findViewById(R.id.deckListViewButton);
        deckListEditMode = (Button) findViewById(R.id.deckListEditButton);
        deckListDeleteMode = (Button) findViewById(R.id.deckListDeleteButton);
        deckName = (TextView) findViewById(R.id.deckName);
        deckFormat = (TextView) findViewById(R.id.deckFormat);
        instructions = (TextView) findViewById(R.id.deckListInstructions);

        //Initialises Button States
        deckListViewMode.setBackgroundColor(Color.RED);
        deckListEditMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        instructions.setText(R.string.deckViewModeHint);


        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String format = in.getStringExtra("format");

        filename = name + "MagikardDeck.txt";

        //
        File file = getBaseContext().getFileStreamPath(filename);
        if(file.exists())
        {
            Log.i("Success", "File found");

        } else {
            Log.i("Error", "File not found");

        }

        loadFromFile(filename, getApplicationContext());

        deckName.setText(name);
        deckFormat.setText(format);

        //Prepares to save information
        saveData = "";

        //TODO: new read in config, two stage algorithm

        if(name != null) {
            deck.setmName(name);
            deck.setmFormat(format);
            deck.setmPokemonCards(pokemon);
            deck.setmTrainerCards(trainers);
            deck.setmEnergyCards(energy);

            saveData +=  name + "|" + format + "|";
        }
        for (int i = 0; i < deck.getmPokemonCards().size(); i++) {
            if (deck.getmPokemonCards().size() > 0) {
                if (!deck.getmPokemonCards().get(i).getmName().equals("")) {
                    saveData += deck.getmPokemonCards().get(i).getmName() + "/"
                            + deck.getmPokemonCards().get(i).getmHitPoints() + "/"
                            + deck.getmPokemonCards().get(i).getmType() + "/";
                }
            }
        }
        saveData += "|";
        for (int i = 0; i < deck.getmTrainerCards().size(); i++) {
            if (deck.getmTrainerCards().size() > 0) {
                if (!deck.getmTrainerCards().get(i).getmName().equals("")) {
                    saveData += deck.getmTrainerCards().get(i).getmName() + "/"
                            + deck.getmTrainerCards().get(i).getmType() + "/"
                            + deck.getmTrainerCards().get(i).getmDescription() + "/";
                }
            }
        }
        saveData += "|";
        for (int i = 0; i < deck.getmEnergyCards().size(); i++) {
            if (deck.getmEnergyCards().size() > 0) {
                if (!deck.getmEnergyCards().get(i).getmName().equals("")) {
                    saveData += deck.getmEnergyCards().get(i).getmName() + "/"
                            + deck.getmEnergyCards().get(i).getmDesc() + "/";
                }
            }
        }


        writeToFile(saveData, getApplicationContext());
        loadFromFile(filename, getApplicationContext());

        getIntent().removeExtra("name");
        getIntent().removeExtra("format");

        //Sets the list view to the array
        DeckListViewAdapter deckListAdapter = new DeckListViewAdapter(this, R.layout.item_decklist, decks); //TODO: Multiple Adapters

        deckListView.setAdapter(deckListAdapter);

        //Handles click on items in the list
        deckListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = deck.getmName();
                String format = deck.getmFormat();

                //Handles clicks dependent on mode//TODO: Conditionals to view different types of cards, detection
                if (ViewMode) {

                    Intent intent = new Intent(DeckListContentViewActivity.this, //TODO: here
                            MainActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("format", format);

                    startActivity(intent);

                }
                if (EditMode) {

                    decks.get(position).setDelete();

                    saveData = "";

                    for (int i = 0; i < decks.size(); i++) {
                        String del = String.valueOf(decks.get(i).isDelete());
                        if (decks.size() > 0 && !decks.get(i).getmName().equals("") && del.equals("false")) {
                            saveData += decks.get(i).getmName() + "|" +
                                    decks.get(i).getmFormat() + "|" +
                                    //TODO: here
                                    decks.get(i).isDelete() + "|";
                        }
                    }

                    writeToFile(saveData, getApplicationContext());
                    loadFromFile(filename, getApplicationContext());

                    Intent intent = new Intent(DeckListLibViewActivity.this, NewDeckListActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("format", format);

                    startActivity(intent);

                }
                if (DeleteMode) {

                    decks.get(position).setDelete();
                    if(decks.get(position).isDelete()) {
                        parent.getChildAt(position).setBackgroundColor(Color.RED);
                    } else {
                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                    }

                    deckListDeleteMode.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                            saveData = "";

                            for (int i = 0; i < decks.size(); i++) {
                                String del = String.valueOf(decks.get(i).isDelete());
                                if (decks.size() > 0 && !decks.get(i).getmName().equals("") && del.equals("false")) {
                                    saveData += decks.get(i).getmName() + "|" +
                                            decks.get(i).getmFormat() + "|" +
                                            //TODO: here
                                            decks.get(i).isDelete() + "|";
                                }
                            }

                            writeToFile(saveData, getApplicationContext());
                            loadFromFile(filename, getApplicationContext());

                            Intent intent = getIntent();
                            //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            return true;

                        }
                    });
                }
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
        pokemon.clear();
        trainers.clear();
        energy.clear();
        try {
            FileInputStream fis = ctx.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            BufferedReader in = new BufferedReader(isr);
            String str;
            String name = "";
            String format = "";
            String strPokemon = "";
            String strTrainer = "";
            String strEnergy = "";
            if((str = in.readLine()) != null) {
                while (str.contains("|")) {
                    name = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    format = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    strPokemon = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    strTrainer = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);

                    strEnergy = str.substring(0, str.indexOf("|"));
                    str = str.substring(str.indexOf("|") + 1);
                }
                deckName.setText(name);
                deckFormat.setText(format);
                while (strPokemon.contains("/")) {
                    String pName = strPokemon.substring(0, strPokemon.indexOf("/"));
                    strPokemon = strPokemon.substring(strPokemon.indexOf("/") + 1);

                    String pHP = strPokemon.substring(0, strPokemon.indexOf("/"));
                    strPokemon = strPokemon.substring(strPokemon.indexOf("/") + 1);

                    String pType = strPokemon.substring(0, strPokemon.indexOf("/"));
                    strPokemon = strPokemon.substring(strPokemon.indexOf("/") + 1);

                    String pStrDelete = strPokemon.substring(0, strPokemon.indexOf("/"));
                    strPokemon = strPokemon.substring(strPokemon.indexOf("/") + 1);

                    if (pStrDelete.equals("false")) {
                        if (!pName.equals("null")) {
                            pokemon.add(new PokemonCard(pName, pHP, pType, false));
                        }
                    }
                }
                while (strTrainer.contains("/")) {
                    String tName = strTrainer.substring(0, strTrainer.indexOf("/"));
                    strPokemon = strTrainer.substring(strTrainer.indexOf("/") + 1);

                    String tType = strTrainer.substring(0, strTrainer.indexOf("/"));
                    strTrainer = strTrainer.substring(strTrainer.indexOf("/") + 1);

                    String tDesc = strTrainer.substring(0, strTrainer.indexOf("/"));
                    strTrainer = strTrainer.substring(strTrainer.indexOf("/") + 1);

                    String tStrDelete = strTrainer.substring(0, strTrainer.indexOf("/"));
                    strTrainer = strTrainer.substring(strTrainer.indexOf("/") + 1);

                    if (tStrDelete.equals("false")) {
                        if (!tName.equals("null")) {
                            trainers.add(new TrainerCard(tName, tType, tDesc, false));
                        }
                    }
                }
                while (strEnergy.contains("/")) {
                    String eName = strEnergy.substring(0, strEnergy.indexOf("/"));
                    strEnergy = strEnergy.substring(strEnergy.indexOf("/") + 1);

                    String eDesc = strEnergy.substring(0, strEnergy.indexOf("/"));
                    strEnergy = strEnergy.substring(strEnergy.indexOf("/") + 1);

                    String eStrDelete = strEnergy.substring(0, strEnergy.indexOf("/"));
                    strEnergy = strEnergy.substring(strEnergy.indexOf("/") + 1);

                    if (eStrDelete.equals("false")) {
                        if (!eName.equals("null")) {
                            energy.add(new EnergyCard(eName, eDesc, false));
                        }
                    }
                }
            }

            isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onClickViewButton (View view) {
        deckListViewMode.setBackgroundColor(Color.RED);
        deckListEditMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        ViewMode = true;
        EditMode = false;
        DeleteMode = false;
        instructions.setText(R.string.contentViewModeHint);

    }

    public void onClickEditButton (View view) {
        deckListViewMode.setBackgroundColor(Color.LTGRAY);
        deckListEditMode.setBackgroundColor(Color.RED);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        ViewMode = false;
        EditMode = true;
        DeleteMode = false;
        instructions.setText(R.string.contentEditModeHint);

    }

    public void onClickDeleteButton (View view) {
        deckListViewMode.setBackgroundColor(Color.LTGRAY);
        deckListEditMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.RED);
        ViewMode = false;
        EditMode = false;
        DeleteMode = true;
        instructions.setText(R.string.contentDeleteModeHint);

    }

}
