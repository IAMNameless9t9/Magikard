package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mannnl on 12/6/2017.
 */

public class NewTrainerCardActivity extends AppCompatActivity {

    EditText newTrainerCardNameEditText;
    EditText newTrainerCardTypeEditText;
    EditText newTrainerCardDescEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_new_card);

        newTrainerCardNameEditText = (EditText) findViewById(R.id.newTrainerCardName);
        newTrainerCardTypeEditText = (EditText) findViewById(R.id.newTrainerCardType);
        newTrainerCardDescEditText = (EditText) findViewById(R.id.newTrainerCardDesc);

        //Coming back from PokemonLibViewActivity in edit mode
        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String type = in.getStringExtra("type");
        String desc = in.getStringExtra("desc");

        newTrainerCardNameEditText.setText(name);
        newTrainerCardTypeEditText.setText(type);
        newTrainerCardDescEditText.setText(desc);

        //Send information to PokemonLibViewActivity
        submitButton = (Button) findViewById(R.id.newPokemonCardSubmitButton);
        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                String name = newTrainerCardNameEditText.getText().toString().trim();
                String type = newTrainerCardTypeEditText.getText().toString().trim();
                String desc = newTrainerCardDescEditText.getText().toString().trim();

                if (name.length() == 0 || type.length() == 0 || desc.length() == 0 ) {
                    Toast.makeText(NewTrainerCardActivity.this,
                            "Please fill out all information", Toast.LENGTH_SHORT).show();

                    // TODO: allow user to leave certain fields empty
                    // TODO: complete all entry fields

                }else {

                    Intent intent = new Intent(NewTrainerCardActivity.this, TrainerLibViewActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("type", type);
                    intent.putExtra("desc", desc);

                    newTrainerCardNameEditText.setText("");
                    newTrainerCardTypeEditText.setText("");
                    newTrainerCardDescEditText.setText("");

                    startActivity(intent);
                }
            }
        });
    }

}



