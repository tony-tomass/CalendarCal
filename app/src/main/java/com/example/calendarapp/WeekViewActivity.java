package com.example.calendarapp;

import static com.example.calendarapp.CalendarUtils.daysInMonthArray;
import static com.example.calendarapp.CalendarUtils.daysInWeekArray;
import static com.example.calendarapp.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView month_header_tv;
    private TextView year_header_tv;
    private Button next_week_bt;
    private Button prev_week_bt;
    private Button add_event_bt;
    private RecyclerView calendar_rv;
    private ListView event_list_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        //CalendarUtils.selected_date = LocalDate.now();
        setupCalendar();
    }

    private void initWidgets() {
        calendar_rv = findViewById(R.id.calendar_RV);
        month_header_tv = findViewById(R.id.month_header_TV);
        year_header_tv = findViewById(R.id.year_header_TV);
        next_week_bt = findViewById(R.id.next_week_BT);
        prev_week_bt = findViewById(R.id.prev_week_BT);
        add_event_bt = findViewById(R.id.add_event_BT);
        event_list_lv = findViewById(R.id.event_list_LV);
        initListeners();
    }

    private void initListeners() {
        prev_week_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selected_date = CalendarUtils.selected_date.minusMonths(1);
                setupCalendar();
            }
        });
        next_week_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selected_date = CalendarUtils.selected_date.plusMonths(1);
                setupCalendar();
            }
        });
        add_event_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EventEditActivity.class));
            }
        });
    }

    private void setupCalendar() {
        month_header_tv.setText(CalendarUtils.selected_date.getMonth().toString());
        year_header_tv.setText(String.valueOf(CalendarUtils.selected_date.getYear()));
        ArrayList<LocalDate> days_in_week = daysInWeekArray(CalendarUtils.selected_date);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days_in_week, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7); //7 columns
        calendar_rv.setLayoutManager(layoutManager);
        calendar_rv.setAdapter(calendarAdapter);
        setEventAdapter();
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        //String message = "Selected date: " + date;
        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        CalendarUtils.selected_date = date;
        setupCalendar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupCalendar();
    }

    private void setEventAdapter() {
        ArrayList<Event> events = Event.eventsForDate(CalendarUtils.selected_date);
        EventAdapter adapter = new EventAdapter(getApplicationContext(), events);
        event_list_lv.setAdapter(adapter);
    }
}