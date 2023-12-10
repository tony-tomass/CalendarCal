package com.example.calendarapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
    private Button new_event_time_bt;
    private Button cancel_event_bt;
    private Button add_event_bt;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog timePickerDialog;
    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private LocalDate new_selected_date;
    private LocalTime new_selected_time;
    //private DateTimeFormatter dateFormatter;
    //private DateTimeFormatter timeFormatter;
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
        new_selected_time = LocalTime.now();

        new_event_name_et = view.findViewById(R.id.new_event_name_ET);
        cancel_event_bt = view.findViewById(R.id.cancel_new_event_BT);
        new_event_date_bt = view.findViewById(R.id.new_event_date_BT);
        new_event_time_bt = view.findViewById(R.id.new_event_time_BT);
        add_event_bt = view.findViewById(R.id.add_new_event_BT);

        //dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        //timeFormatter = DateTimeFormatter.ofPattern("h:mm a");
        String formatted_date = CalendarUtils.selected_date.format(CalendarUtils.dateFormatter_MED);
        String formatted_time = LocalTime.now().format(CalendarUtils.timeFormatter_12HR);
        new_event_date_bt.setText(formatted_date);
        new_event_time_bt.setText(formatted_time);

        initListeners();
    }

    private void initListeners() {
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                int new_month = month + 1;
                new_selected_date = LocalDate.of(year, new_month, day);
                String formatted_date = new_selected_date.format(CalendarUtils.dateFormatter_MED);
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
        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                new_selected_time = LocalTime.of(hour, minute);
                Log.i("TIME", new_selected_time.toString());
                String formatted_time = new_selected_time.format(CalendarUtils.timeFormatter_12HR);
                new_event_time_bt.setText(formatted_time);
            }
        };
        new_event_time_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalTime time = LocalTime.now();
                int hour = time.getHour();
                int minute = time.getMinute();

                timePickerDialog = new TimePickerDialog(
                        getActivity(),
                        R.style.TimePickerStyle,
                        timeSetListener,
                        hour, minute, false);
                timePickerDialog.show();
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
                //Log.i("ADDING_TIME", new_selected_time.toString());
                Event new_event = new Event(name, new_selected_date, new_selected_time);
                Event.events_list.add(new_event);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}