package com.example.calendarapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

//Creating pop-up Dialog Fragments
//https://developer.android.com/develop/ui/views/components/dialogs
//TODO: Look into DatePickerDialog
public class AddEvent_DialogFragment extends DialogFragment {

    public AddEvent_DialogFragment() {
        // Required empty public constructor
    }

    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event_dialog, container, false);
        return view;
    }
     */

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(getActivity());
        dialog_builder.setMessage(R.string.add_event_title_dialog)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Add Event
            }})
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancel adding
            }});
        return dialog_builder.create();
    }
}