package Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import activities.LoadScreenActivity;
import activities.MainActivity;

/**
 * Created by corpa on Aug 20, 2016
 */
public class InternetDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("You are not connected to the internet!")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
        return builder.create();
    }

}