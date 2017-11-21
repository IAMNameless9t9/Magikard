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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by mannnl on 11/1/2017.
 */

public class PokedexViewActivity extends AppCompatActivity {


    ListView pokedexListView;
    ArrayList<PokemonCard> cards = new ArrayList<>();
    private String filename = "pokemonCards.txt";
    private String saveData;
    Button pokedexViewMode;
    Button pokedexEditMode;
    Button pokedexDeleteMode;
    Boolean ViewMode = true;
    Boolean EditMode = false;
    Boolean DeleteMode = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex_view);


        pokedexListView = (ListView) findViewById(R.id.pokedexListView);
        pokedexViewMode = (Button) findViewById(R.id.pokedexViewButton);
        pokedexEditMode = (Button) findViewById(R.id.pokedexEditButton);
        pokedexDeleteMode = (Button) findViewById(R.id.pokedexDeleteButton);



        File file = getBaseContext().getFileStreamPath(filename);
        if(file.exists())
        {
            Log.i("Success", "File found");

        } else {
            Log.i("Error", "File not found");

        }


        loadFromFile(filename, getApplicationContext());

        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String HP = in.getStringExtra("HP");
        String type = in.getStringExtra("type");

        saveData = "";

        if(name != null) {
            cards.add(new PokemonCard(name, HP, type));
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.size() > 0) {
                if (!cards.get(i).getmName().equals(null)) {
                    saveData += cards.get(i).getmName() + "/" +
                            cards.get(i).getmHitPoints() + "/" +
                            cards.get(i).getmType() + "/";
                }
            }
        }

        writeToFile(saveData, getApplicationContext());
        loadFromFile(filename, getApplicationContext());

        PokedexAdapter pokedexAdapter = new PokedexAdapter(this, R.layout.item_pokemon_card, cards);

        pokedexListView.setAdapter(pokedexAdapter);

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
        cards.clear();
        int i = 0;
        try {
            FileInputStream fis = ctx.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            BufferedReader in = new BufferedReader(isr);
            String str;
            if((str = in.readLine()) != null) {
                while (str.contains("/")) {
                    String name = str.substring(0, str.indexOf("/"));
                    str = str.substring(str.indexOf("/") + 1);

                    String HP = str.substring(0, str.indexOf("/"));
                    str = str.substring(str.indexOf("/") + 1);

                    String type = str.substring(0, str.indexOf("/"));
                    str = str.substring(str.indexOf("/") + 1);

                    if (name.equals("null")) {
                    }else{
                        cards.add(new PokemonCard(name, HP, type));
                    }
                }
            }
           isr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onItemClickPokedexListView(AdapterView<?> parent, View view, int pos, long id) {

        System.out.println(view);
        System.out.println(pos);

    }



    public void onClickViewButton (View view) {
        pokedexViewMode.setBackgroundColor(Color.RED);
        pokedexEditMode.setBackgroundColor(Color.LTGRAY);
        pokedexDeleteMode.setBackgroundColor(Color.LTGRAY);
        Boolean ViewMode = true;
        Boolean EditMode = false;
        Boolean DeleteMode = false;

    }

    public void onClickEditButton (View view) {
        pokedexViewMode.setBackgroundColor(Color.LTGRAY);
        pokedexEditMode.setBackgroundColor(Color.RED);
        pokedexDeleteMode.setBackgroundColor(Color.LTGRAY);
        Boolean ViewMode = false;
        Boolean EditMode = true;
        Boolean DeleteMode = false;
    }

    public void onClickDeleteButton (View view) {
        pokedexViewMode.setBackgroundColor(Color.LTGRAY);
        pokedexEditMode.setBackgroundColor(Color.LTGRAY);
        pokedexDeleteMode.setBackgroundColor(Color.RED);
        Boolean ViewMode = false;
        Boolean EditMode = false;
        Boolean DeleteMode = true;
    }

}
