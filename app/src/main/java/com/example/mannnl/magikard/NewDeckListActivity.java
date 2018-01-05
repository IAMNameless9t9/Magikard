package com.example.mannnl.magikard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mannnl on 1/5/2018.
 */

public class NewDeckListActivity extends AppCompatActivity{

    EditText newDeckListNameEditText;
    EditText newDeckListFormatEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_decklist);

        newDeckListNameEditText = (EditText) findViewById(R.id.newDeckListName);
        newDeckListFormatEditText = (EditText) findViewById(R.id.newDeckListFormat);

        Intent in = this.getIntent();
        String name = in.getStringExtra("name");
        String format = in.getStringExtra("format");

        newDeckListNameEditText.setText(name);
        newDeckListFormatEditText.setText(format);

        submitButton = (Button) findViewById(R.id.newDeckListSubmitButton);
        submitButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                String name = newDeckListNameEditText.getText().toString().trim();
                String format = newDeckListFormatEditText.getText().toString().trim();

                if (name.length() == 0 || format.length() == 0 ) {
                    Toast.makeText(NewDeckListActivity.this,
                            "Please fill out all information", Toast.LENGTH_SHORT).show();

                    // TODO: Selectable options for format

                }else {

                    Intent intent = new Intent(NewDeckListActivity.this, DeckListLibViewActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("format", format);

                    newDeckListNameEditText.setText("");
                    newDeckListFormatEditText.setText("");

                    startActivity(intent);
                }
            }
        });
    }

}
