package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    TextView month_header_tv;
    TextView year_header_tv;
    RecyclerView calendar_rv;
    LocalDate selected_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        selected_date = LocalDate.now();
        setupCalendar();
    }

    private void initWidgets() {
        calendar_rv = findViewById(R.id.calendar_RV);
        month_header_tv = findViewById(R.id.month_header_TV);
        year_header_tv = findViewById(R.id.year_header_TV);

    }

    private void setupCalendar() {
        month_header_tv.setText(selected_date.getMonth().toString());
        year_header_tv.setText(String.valueOf(selected_date.getYear()));
        ArrayList<String> days_in_month = daysInMonthArray(selected_date);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days_in_month, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7); //7 columns
        calendar_rv.setLayoutManager(layoutManager);
        calendar_rv.setAdapter(calendarAdapter);

    }

    private ArrayList<String> daysInMonthArray(LocalDate localDate) {
        ArrayList<String> days_in_month_array = new ArrayList<>();
        YearMonth year_month = YearMonth.from(localDate);   //Gets year and month
        int days_in_month = year_month.lengthOfMonth();   //Gets number of days in above month
        //Log.i("daysinmonth", String.valueOf(days_in_month));
        LocalDate first_of_month = localDate.withDayOfMonth(1);   //Gets date of first day of month
        int day_of_week = first_of_month.getDayOfWeek().getValue() % 7;   //Gets what day of week first day appears; mod 7 is used b/c without it, a whole row gets skipped if the first day is on Sunday (array starts at 0 but getDayOfWeek starts at 1)
        //Log.i("dayofweek", String.valueOf(day_of_week));

        for (int i = 1; i <= 38; i++) {
            if (i <= day_of_week || i > days_in_month + day_of_week) {   //If after the first or last day of the month
                days_in_month_array.add("");
            }
            else {
                days_in_month_array.add("" + (i - day_of_week));
            }
        }

        return days_in_month_array;
    }

    private String monthYearFromDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return localDate.format(formatter);
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals(""))
        {
            String message = "Selected date: " + dayText + " " + monthYearFromDate(selected_date);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public void prevMonthAction(View view) {
        selected_date = selected_date.minusMonths(1);
        setupCalendar();
    }

    public void nextMonthAction(View view) {
        selected_date = selected_date.plusMonths(1);
        setupCalendar();
    }
}