package com.example.calendarapp;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AddEvent_Fragment extends Fragment {

    private EditText new_event_name_et;
    private Button new_event_date_bt;
    private Button cancel_event_bt;
    private Button add_event_bt;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    //private LocalDate date;
    private LocalTime time;
    private LocalDate new_selected_date;
    private DateTimeFormatter formatter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        initWidgets(view);
        return view;
    }

    private void initWidgets(View view) {
        new_selected_date = CalendarUtils.selected_date;
        new_event_name_et = view.findViewById(R.id.new_event_name_ET);
        cancel_event_bt = view.findViewById(R.id.cancel_new_event_BT);
        new_event_date_bt = view.findViewById(R.id.new_event_date_BT);
        add_event_bt = view.findViewById(R.id.add_new_event_BT);

        formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String formatted_date = CalendarUtils.selected_date.format(formatter);
        new_event_date_bt.setText(formatted_date);

        initListeners();
    }

    private void initListeners() {
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                int new_month = month + 1;
                new_selected_date = LocalDate.of(year, new_month, day);
                String formatted_date = new_selected_date.format(formatter);
                //Log.i("new_date", new_selected_date.toString());
                new_event_date_bt.setText(formatted_date);
            }
        };
        new_event_date_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate date = CalendarUtils.selected_date;
                //Log.i("DATE", date.toString());
                int day = date.getDayOfMonth();
                int month = date.getMonth().getValue()-1;   //Month - 1 b/c the picker starts one month after for some reason
                int year = date.getYear();

                datePickerDialog = new DatePickerDialog(
                        getActivity(),
                        R.style.DatePickerStyle,
                        dateSetListener,
                        year, month, day);
                datePickerDialog.show();
            }
        });
        cancel_event_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        add_event_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = new_event_name_et.getText().toString().trim();
                if (name.isEmpty()) {
                    name = "(no name)";
                }
                LocalTime time = LocalTime.now();   //Placeholder
                Event new_event = new Event(name, new_selected_date, time);
                Event.events_list.add(new_event);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}