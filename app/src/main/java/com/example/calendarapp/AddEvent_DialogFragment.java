package com.example.calendarapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.time.LocalTime;

//Creating pop-up Dialog Fragments
//https://developer.android.com/develop/ui/views/components/dialogs
//TODO: Look into DatePickerDialog
public class AddEvent_DialogFragment extends DialogFragment{

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

    /*
    public interface AddEventListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    AddEventListener add_event_listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            add_event_listener = (AddEventListener) context;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement AddEventListener");
        }
    }
     */

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //super.onCreateDialog(savedInstanceState);
        //https://stackoverflow.com/questions/18346920/change-the-background-color-of-a-pop-up-dialog
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(getActivity(), R.style.dialog_theme);
        /*
        //For the default style
        dialog_builder.setMessage(R.string.add_event_title_dialog)
                .setTitle("NEW EVENT")
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

         */
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_event_dialog, null);
        dialog_builder.setView(view)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Add Event
                        //add_event_listener.onDialogPositiveClick(AddEvent_DialogFragment.this);
                        EditText name = view.findViewById(R.id.add_event_name_ET);
                        EditText date = view.findViewById(R.id.add_event_date_ET);
                        EditText time = view.findViewById(R.id.add_event_time_ET);
                        //TODO: Add actual working date and time fields
                        Event new_event = new Event(name.getText().toString(), CalendarUtils.selected_date, LocalTime.now());
                        Event.events_list.add(new_event);
                        AddEvent_DialogFragment.this.getDialog().dismiss();
                    }})
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Cancel adding
                        AddEvent_DialogFragment.this.getDialog().cancel();
                    }});
        return dialog_builder.create();
    }
}