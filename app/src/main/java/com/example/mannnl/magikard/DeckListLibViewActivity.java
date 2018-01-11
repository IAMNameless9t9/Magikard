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

public class DeckListLibViewActivity extends AppCompatActivity {

    ListView deckListView;
    ArrayList<DeckList> decks = new ArrayList<>();
    private String filename = "deckLists.txt";
    private String saveData;
    ArrayList<PokemonCard> pokemon;
    ArrayList<TrainerCard> trainers;
    ArrayList<EnergyCard> energy;
    Button deckListViewMode;
    Button deckListEditMode;
    Button deckListDeleteMode;
    Boolean ViewMode = true;
    Boolean EditMode = false;
    Boolean DeleteMode = false;
    TextView instructions;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decklist_lib_view);


        deckListView = (ListView) findViewById(R.id.deckListView);
        deckListViewMode = (Button) findViewById(R.id.deckListViewButton);
        deckListEditMode = (Button) findViewById(R.id.deckListEditButton);
        deckListDeleteMode = (Button) findViewById(R.id.deckListDeleteButton);
        instructions = (TextView) findViewById(R.id.deckListInstructions);

        //Initialises Button States
        deckListViewMode.setBackgroundColor(Color.RED);
        deckListEditMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        instructions.setText(R.string.deckViewModeHint);

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
        String name = in.getStringExtra("name");
        String format = in.getStringExtra("format");

        //Prepares to save information
        saveData = "";

        if(name != null) {
            decks.add(new DeckList(name, format, pokemon, trainers, energy, false));
        }
        for (int i = 0; i < decks.size(); i++) {
            if (decks.size() > 0) {
                if (!decks.get(i).getmName().equals("")) {
                    saveData += decks.get(i).getmName() + "/" +
                            decks.get(i).getmFormat() + "/" +
                            decks.get(i).isDelete() + "/";
                }
            }
        }

        writeToFile(saveData, getApplicationContext());
        loadFromFile(filename, getApplicationContext());

        getIntent().removeExtra("name");
        getIntent().removeExtra("format");

        //Sets the list view to the array
        DeckListViewAdapter deckListAdapter = new DeckListViewAdapter(this, R.layout.item_decklist, decks);

        deckListView.setAdapter(deckListAdapter);

        //Handles click on items in the list
        deckListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = decks.get(position).getmName();
                String format = decks.get(position).getmFormat();

                //Handles clicks dependent on mode
                if (ViewMode) {

                    Intent intent = new Intent(DeckListLibViewActivity.this, MainActivity.class);
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
                            saveData += decks.get(i).getmName() + "/" +
                                    decks.get(i).getmFormat() + "/" +
                                    decks.get(i).isDelete() + "/";
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
                                    saveData += decks.get(i).getmName() + "/" +
                                            decks.get(i).getmFormat() + "/" +
                                            decks.get(i).isDelete() + "/";
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
        decks.clear();
        try {
            FileInputStream fis = ctx.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

            BufferedReader in = new BufferedReader(isr);
            String str;
            if((str = in.readLine()) != null) {
                while (str.contains("/")) {
                    String name = str.substring(0, str.indexOf("/"));
                    str = str.substring(str.indexOf("/") + 1);

                    String format = str.substring(0, str.indexOf("/"));
                    str = str.substring(str.indexOf("/") + 1);

                    String strDelete = str.substring(0, str.indexOf("/"));
                    str = str.substring(str.indexOf("/") + 1);

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

    public void onClickViewButton (View view) {
        deckListViewMode.setBackgroundColor(Color.RED);
        deckListEditMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        ViewMode = true;
        EditMode = false;
        DeleteMode = false;
        instructions.setText(R.string.deckViewModeHint);

    }

    public void onClickEditButton (View view) {
        deckListViewMode.setBackgroundColor(Color.LTGRAY);
        deckListEditMode.setBackgroundColor(Color.RED);
        deckListDeleteMode.setBackgroundColor(Color.LTGRAY);
        ViewMode = false;
        EditMode = true;
        DeleteMode = false;
        instructions.setText(R.string.deckEditModeHint);

    }

    public void onClickDeleteButton (View view) {
        deckListViewMode.setBackgroundColor(Color.LTGRAY);
        deckListEditMode.setBackgroundColor(Color.LTGRAY);
        deckListDeleteMode.setBackgroundColor(Color.RED);
        ViewMode = false;
        EditMode = false;
        DeleteMode = true;
        instructions.setText(R.string.deckDeleteModeHint);

    }

}
