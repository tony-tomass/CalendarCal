package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {

    private EditText event_name_et;
    private TextView event_time_tv;
    private TextView event_date_tv;
    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        initWidgets();
        time = LocalTime.now();
        event_date_tv.setText("Date: " + CalendarUtils.formatDate(CalendarUtils.selected_date));
        event_time_tv.setText("Time: " + CalendarUtils.formatTime(time));
    }

    private void initWidgets() {
        event_name_et = findViewById(R.id.event_name_ET);
        event_time_tv = findViewById(R.id.event_time_TV);
        event_date_tv = findViewById(R.id.event_date_TV);
    }

    public void saveEventAction(View view) {
        String event_name = event_name_et.getText().toString().trim();
        Event new_event = new Event(event_name, CalendarUtils.selected_date, time);
        Event.events_list.add(new_event);
        finish();
    }
}