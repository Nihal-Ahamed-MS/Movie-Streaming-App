package com.example.moviestreamingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class LinkDialog extends AppCompatDialogFragment {
    private EditText addLink;
    private addDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (addDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Include the addDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_addllink,null);
        alertDialog.setView(view)
                .setTitle("Add Subject")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String subject = addLink.getText().toString();
                        listener.valesFromEditText(subject);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LinkDialog.this.getDialog().cancel();
                    }
                });

        addLink = view.findViewById(R.id.addLink);

        return alertDialog.create();
    }

    public interface addDialogListener{
        void valesFromEditText(String link);
    }
}
