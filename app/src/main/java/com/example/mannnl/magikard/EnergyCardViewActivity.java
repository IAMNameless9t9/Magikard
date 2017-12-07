package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by mannnl on 11/30/2017.
 */

public class EnergyCardViewActivity extends AppCompatActivity {

    TextView cardName;
    TextView cardDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_card_view);

        cardName = (TextView) findViewById(R.id.cardName);
        cardDesc = (TextView) findViewById(R.id.cardHP);

        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String desc = in.getStringExtra("desc");

        cardName.setText(name);
        cardDesc.setText(desc);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, EnergyLibViewActivity.class);
        startActivity(intent);
    }

}
