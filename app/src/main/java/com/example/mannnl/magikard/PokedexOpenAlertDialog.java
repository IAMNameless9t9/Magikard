package com.example.mannnl.magikard;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


public class PokedexOpenAlertDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialogPokedexOpen)
                .setItems(R.array.option_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0) {
                            Intent intent = new Intent(getContext(), PokemonLibViewActivity.class);
                            startActivity(intent);
                        }
                        if(which == 1) {
                            Intent intent = new Intent(getContext(), TrainerLibViewActivity.class);
                            startActivity(intent);
                        }
                        if(which == 2) {
                            Intent intent = new Intent(getContext(), EnergyLibViewActivity.class);
                            startActivity(intent);
                        }
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

}
