package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by mannnl on 11/30/2017.
 */

public class PokemonCardViewActivity extends AppCompatActivity {

    TextView cardName;
    TextView cardHP;
    TextView cardType;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_card_view);

        cardName = (TextView) findViewById(R.id.cardName);
        cardHP = (TextView) findViewById(R.id.cardHP);
        cardType = (TextView) findViewById(R.id.cardType);

        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String HP = in.getStringExtra("HP");
        String type = in.getStringExtra("type");

        cardName.setText(name);
        cardHP.setText(HP);
        cardType.setText(type);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PokemonLibViewActivity.class);
        startActivity(intent);
    }

}
