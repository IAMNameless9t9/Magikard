package com.example.mannnl.magikard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by mannnl on 12/5/2017.
 */

public class NewCardTypeAlertDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialogNewCard)
        .setItems(R.array.option_array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if(which == 1) {
                    startActivity(new Intent(getBaseContext(),NewPokemonCardActivity.class));
                }
                if(which == 2) {

                }
                if(which == 3) {

                }
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
