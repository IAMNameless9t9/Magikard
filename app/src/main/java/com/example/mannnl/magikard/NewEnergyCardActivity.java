package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewEnergyCardActivity extends AppCompatActivity {

    EditText newEnergyCardNameEditText;
    EditText newEnergyCardDescEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_new_card);

        newEnergyCardNameEditText = (EditText) findViewById(R.id.newEnergyCardName);
        newEnergyCardDescEditText = (EditText) findViewById(R.id.newEnergyCardDesc);

        //Coming back from PokemonLibViewActivity in edit mode
        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String desc = in.getStringExtra("desc");

        newEnergyCardNameEditText.setText(name);
        newEnergyCardDescEditText.setText(desc);

        //Send information to PokemonLibViewActivity
        submitButton = (Button) findViewById(R.id.newPokemonCardSubmitButton);
        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                String name = newEnergyCardNameEditText.getText().toString().trim();
                String desc = newEnergyCardDescEditText.getText().toString().trim();

                if (name.length() == 0 || desc.length() == 0) {
                    Toast.makeText(NewEnergyCardActivity.this,
                            "Please fill out all information", Toast.LENGTH_SHORT).show();

                    // TODO: allow user to leave certain fields empty
                    // TODO: complete all entry fields

                }else {

                    Intent intent = new Intent(NewEnergyCardActivity.this, EnergyLibViewActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("desc", desc);

                    newEnergyCardNameEditText.setText("");
                    newEnergyCardDescEditText.setText("");

                    startActivity(intent);
                }
            }
        });
    }

}
