package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mannnl on 11/30/2017.
 */

public class PokemonCardViewActivity extends AppCompatActivity {

    TextView cardName;
    TextView cardHP;
    TextView cardType;
    Button addButton;
    String name;
    String HP;
    String type;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_card_view);

        cardName = (TextView) findViewById(R.id.cardName);
        cardHP = (TextView) findViewById(R.id.cardHP);
        cardType = (TextView) findViewById(R.id.cardType);
        addButton = (Button) findViewById(R.id.addButton);

        Intent in = this.getIntent();
        name = in.getStringExtra("name");
        HP = in.getStringExtra("HP");
        type = in.getStringExtra("type");

        cardName.setText(name);
        cardHP.setText(HP);
        cardType.setText(type);

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void onClickAddButton() {

        Intent intent = new Intent(PokemonCardViewActivity.this, DeckListLibViewActivity.class);
        intent.putExtra("pokemonName", name);
        intent.putExtra("pokemonHP", HP);
        intent.putExtra("pokemonType", type);
        startActivity(intent);

        /*
        TODO: 1) Pass the card as an Object
        TODO: 2) Switch to DeckListViewActivity
        TODO: 3) Create a new "mode" that adds the passed card object to the deck object
        */
    }

}
