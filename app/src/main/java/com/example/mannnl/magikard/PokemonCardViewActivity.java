package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

        System.out.println("IMPORTANT THING: " + name);

    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void onClickAddButton (View view) {

        Intent intent = new Intent(PokemonCardViewActivity.this, DeckListLibAddViewActivity.class);
        intent.putExtra("pokemonName", cardName.getText());
        intent.putExtra("pokemonHP", cardHP.getText());
        intent.putExtra("pokemonType", cardType.getText());
        startActivity(intent);

    }

}
