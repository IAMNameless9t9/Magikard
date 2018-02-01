package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by mannnl on 11/30/2017.
 */

public class TrainerCardViewActivity extends AppCompatActivity {

    TextView cardName;
    TextView cardType;
    TextView cardDesc;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_card_view);

        cardName = (TextView) findViewById(R.id.cardName);
        cardType = (TextView) findViewById(R.id.cardType);
        cardDesc = (TextView) findViewById(R.id.cardDesc);

        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String type = in.getStringExtra("type");
        String desc = in.getStringExtra("desc");

        cardName.setText(name);
        cardType.setText(type);
        cardDesc.setText(desc);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onClickAddButton() {

    }

}
