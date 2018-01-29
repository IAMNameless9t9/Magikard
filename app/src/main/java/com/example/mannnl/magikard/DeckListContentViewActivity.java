package com.example.mannnl.magikard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DeckListContentViewActivity extends AppCompatActivity {

    ListView pokemonListView;
    ListView trainerListView;
    ListView energyListView;
    LinearLayout pokemonListViewLayout;
    LinearLayout trainerListViewLayout;
    LinearLayout energyListViewLayout;
    ArrayList<PokemonCard> pokemon = new ArrayList<>();
    ArrayList<TrainerCard> trainers = new ArrayList<>();
    ArrayList<EnergyCard> energy = new ArrayList<>();
    DeckList deck = new DeckList();
    private String filename;
    private String saveData;
    Button deckListViewMode;
    Button deckListDeleteMode;
    Boolean ViewMode = true;
    Boolean DeleteMode = false;
    TextView deckName;
    TextView deckFormat;
    TextView instructions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decklist_content_view);

        //Wire ups
        pokemonListView = (ListView) findViewById(R.id.pokemonListView);
        trainerListView = (ListView) findViewById(R.id.trainerListView);
        energyListView = (ListView) findViewById(R.id.energyListView);
        pokemonListViewLayout = (LinearLayout) findViewById(R.id.pokemonListViewLayout);
        trainerListViewLayout = (LinearLayout) findViewById(R.id.trainerListViewLayout);
        energyListViewLayout = (LinearLayout) findViewById(R.id.energyListViewLayout);
        deckListViewMode = (Button) findViewById(R.id.deckListViewButton);
        deckListDeleteMode = (Button) findViewById(R.id.deckListDeleteButton);
        deckName = (TextView) findViewById(R.id.deckName);
        deckFormat = (TextView) findViewById(R.id.deckFormat);
        instructions = (TextView) findViewById(R.id.deckListInstructions);

        //Initialises Button States
        deckListViewMode.setBackgroundColor(Color.RED);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        instructions.setText(R.string.deckViewModeHint);

        //TODO: Remove Examples

        pokemon.add(new PokemonCard("Pikachu", "70", "Lightning", false));
        pokemon.add(new PokemonCard("Pikachu", "70", "Lightning", false));
        pokemon.add(new PokemonCard("Pikachu", "70", "Lightning", false));
        pokemon.add(new PokemonCard("Pikachu", "70", "Lightning", false));
        pokemon.add(new PokemonCard("Raichu GX", "210", "Lightning", false));
        pokemon.add(new PokemonCard("Raichu GX", "210", "Lightning", false));
        pokemon.add(new PokemonCard("Raichu GX", "210", "Lightning", false));

        trainers.add(new TrainerCard("Cynthia", "Supporter", "Shuffle-Draw", false));
        trainers.add(new TrainerCard("Cynthia", "Supporter", "Shuffle-Draw", false));
        trainers.add(new TrainerCard("Cynthia", "Supporter", "Shuffle-Draw", false));
        trainers.add(new TrainerCard("Volkner", "Supporter", "Search [L]", false));
        trainers.add(new TrainerCard("Volkner", "Supporter", "Search [L]", false));
        trainers.add(new TrainerCard("Volkner", "Supporter", "Search [L]", false));

        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));
        energy.add(new EnergyCard("Lightning", "Basic Energy", false));

        //TODO: Remove Examples

        //Handle intent passing from NewDeckListActivity
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

        //TODO ERROR
        loadFromFile(filename, getApplicationContext());

        deckName.setText(name);
        deckFormat.setText(format);

        //Prepares to save information
        saveData = "";

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

        //Custom Adapters for various ListViews
        PokemonCardPokedexAdapter pokemonAdapter = new PokemonCardPokedexAdapter(this, R.layout.item_pokemon_card, pokemon);
        pokemonListView.setAdapter(pokemonAdapter);

        TrainerCardPokedexAdapter trainerAdapter = new TrainerCardPokedexAdapter(this, R.layout.item_trainer_card, trainers);
        trainerListView.setAdapter(trainerAdapter);

        EnergyCardPokedexAdapter energyAdapter = new EnergyCardPokedexAdapter(this, R.layout.item_energy_card, energy);
        energyListView.setAdapter(energyAdapter);

        //Handles click on items in the list
        pokemonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Handles clicks dependent on modes
                if (ViewMode) {

                    Intent intent = new Intent(DeckListContentViewActivity.this,
                            PokemonCardViewActivity.class);
                    intent.putExtra("name", pokemon.get(position).getmName());
                    intent.putExtra("HP", pokemon.get(position).getmHitPoints());
                    intent.putExtra("type", pokemon.get(position).getmType());

                    startActivity(intent);

                }
                if (DeleteMode) {

                    pokemon.get(position).setDelete();
                    if(pokemon.get(position).isDelete()) {
                        parent.getChildAt(position).setBackgroundColor(Color.RED);
                    } else {
                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                    }

                    deckListDeleteMode.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                            saveData = "";

                            for (int i = 0; i < pokemon.size(); i++) {
                                String del = String.valueOf(pokemon.get(i).isDelete());
                                if (pokemon.size() > 0 && !pokemon.get(i).getmName().equals("") && del.equals("false")) {
                                    saveData += pokemon.get(i).getmName() + "/" +
                                            pokemon.get(i).getmHitPoints() + "/" +
                                            pokemon.get(i).getmType() + "/" +
                                            pokemon.get(i).isDelete() + "/";
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
        //Handles click on items in the list
        trainerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Handles clicks dependent on modes
                if (ViewMode) {

                    Intent intent = new Intent(DeckListContentViewActivity.this,
                            TrainerCardViewActivity.class);
                    intent.putExtra("name", trainers.get(position).getmName());
                    intent.putExtra("type", trainers.get(position).getmType());
                    intent.putExtra("desc", trainers.get(position).getmDescription());

                    startActivity(intent);

                }
                if (DeleteMode) {

                    trainers.get(position).setDelete();
                    if(trainers.get(position).isDelete()) {
                        parent.getChildAt(position).setBackgroundColor(Color.RED);
                    } else {
                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                    }

                    deckListDeleteMode.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                            saveData = "";

                            for (int i = 0; i < trainers.size(); i++) {
                                String del = String.valueOf(trainers.get(i).isDelete());
                                if (trainers.size() > 0 && !trainers.get(i).getmName().equals("") && del.equals("false")) {
                                    saveData += trainers.get(i).getmName() + "/" +
                                            trainers.get(i).getmType() + "/" +
                                            trainers.get(i).getmDescription() + "/" +
                                            trainers.get(i).isDelete() + "/";
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
        //Handles click on items in the list
        energyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Handles clicks dependent on modes
                if (ViewMode) {

                    Intent intent = new Intent(DeckListContentViewActivity.this,
                            EnergyCardViewActivity.class);
                    intent.putExtra("name", energy.get(position).getmName());
                    intent.putExtra("desc", energy.get(position).getmDesc());

                    startActivity(intent);

                }
                if (DeleteMode) {

                    energy.get(position).setDelete();
                    if(energy.get(position).isDelete()) {
                        parent.getChildAt(position).setBackgroundColor(Color.RED);
                    } else {
                        parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                    }

                    deckListDeleteMode.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {

                            saveData = "";

                            for (int i = 0; i < energy.size(); i++) {
                                String del = String.valueOf(energy.get(i).isDelete());
                                if (energy.size() > 0 && !energy.get(i).getmName().equals("") && del.equals("false")) {
                                    saveData += energy.get(i).getmName() + "/" +
                                            energy.get(i).getmDesc() + "/" +
                                            energy.get(i).isDelete() + "/";
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

        //Dynamically sets the height of the LinearLayout that contains the ListView to be exactly as long as the list.
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Integer width = metrics.widthPixels;
        Integer itemHeight = 82;

        Integer height = (itemHeight * pokemon.size());
        LinearLayout.LayoutParams mParam = new LinearLayout.LayoutParams(width,height);
        pokemonListViewLayout.setLayoutParams(mParam);

        height = (itemHeight * trainers.size());
        mParam = new LinearLayout.LayoutParams(width,height);
        trainerListViewLayout.setLayoutParams(mParam);

        height = (itemHeight * energy.size());
        mParam = new LinearLayout.LayoutParams(width,height);
        energyListViewLayout.setLayoutParams(mParam);

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
        //pokemon.clear();
        //trainers.clear();
        //energy.clear();
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
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        ViewMode = true;
        DeleteMode = false;
        instructions.setText(R.string.contentViewModeHint);

    }

    public void onClickDeleteButton (View view) {
        deckListViewMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.RED);
        ViewMode = false;
        DeleteMode = true;
        instructions.setText(R.string.contentDeleteModeHint);

    }

}
