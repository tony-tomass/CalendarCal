package com.example.calendarapp;

import static com.example.calendarapp.CalendarUtils.daysInMonthArray;
import static com.example.calendarapp.CalendarUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView month_header_tv;
    private TextView year_header_tv;
    private RecyclerView calendar_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        CalendarUtils.selected_date = LocalDate.now();
        setupCalendar();
    }

    private void initWidgets() {
        calendar_rv = findViewById(R.id.calendar_RV);
        month_header_tv = findViewById(R.id.month_header_TV);
        year_header_tv = findViewById(R.id.year_header_TV);

    }

    private void setupCalendar() {
        month_header_tv.setText(CalendarUtils.selected_date.getMonth().toString());
        year_header_tv.setText(String.valueOf(CalendarUtils.selected_date.getYear()));
        ArrayList<LocalDate> days_in_month = daysInMonthArray(CalendarUtils.selected_date);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days_in_month, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7); //7 columns
        calendar_rv.setLayoutManager(layoutManager);
        calendar_rv.setAdapter(calendarAdapter);

    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null)
        {
            //String message = "Selected date: " + date;
            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            CalendarUtils.selected_date = date;
            setupCalendar();
        }
    }

    public void prevMonthAction(View view) {
        CalendarUtils.selected_date = CalendarUtils.selected_date.minusMonths(1);
        setupCalendar();
    }

    public void nextMonthAction(View view) {
        CalendarUtils.selected_date = CalendarUtils.selected_date.plusMonths(1);
        setupCalendar();
    }

    public void weekly_action(View view) {
        startActivity(new Intent(getApplicationContext(), WeekViewActivity.class));
    }
}