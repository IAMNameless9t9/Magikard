package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NewCardActivity extends AppCompatActivity {

    EditText newPokemonCardNameEditText;
    EditText newPokemonCardHPEditText;
    EditText newPokemonCardTypeEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);


        newPokemonCardNameEditText = (EditText) findViewById(R.id.newPokemonCardName);
        newPokemonCardHPEditText = (EditText) findViewById(R.id.newPokemonCardHP);
        newPokemonCardTypeEditText = (EditText) findViewById(R.id.newPokemonCardType);

        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String HP = in.getStringExtra("HP");
        String type = in.getStringExtra("type");

        newPokemonCardNameEditText.setText(name);
        newPokemonCardHPEditText.setText(HP);
        newPokemonCardTypeEditText.setText(type);

        submitButton = (Button) findViewById(R.id.newPokemonCardSubmitButton);
        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

        String name = newPokemonCardNameEditText.getText().toString().trim();
        String HP = newPokemonCardHPEditText.getText().toString().trim();
        String type = newPokemonCardTypeEditText.getText().toString().trim();

                if (name.length() == 0 || HP.length() == 0 || type.length() == 0 ) {
                    Toast.makeText(NewCardActivity.this,
                            "Please fill out all information", Toast.LENGTH_SHORT).show();

                    // TODO: allow user to leave certain fields empty
                    // TODO: complete all entry fields

                }else {

                    Intent intent = new Intent(NewCardActivity.this, PokedexViewActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("HP", HP);
                    intent.putExtra("type", type);

                    newPokemonCardNameEditText.setText("");
                    newPokemonCardHPEditText.setText("");
                    newPokemonCardTypeEditText.setText("");

                    startActivity(intent);
                }
            }
        });
    }
}
