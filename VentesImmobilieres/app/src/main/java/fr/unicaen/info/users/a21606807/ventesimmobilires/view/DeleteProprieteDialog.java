package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class DeleteProprieteDialog extends DialogFragment {

    private DialogListener listener;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Voulez-vous supprimer la propriété de votre appareil ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.onPositiveButton();
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.onNegativeButton();
            }
        });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " n'est pas une instance de DialogListener");
        }
    }
}
