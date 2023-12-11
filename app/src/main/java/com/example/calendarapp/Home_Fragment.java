package com.example.calendarapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class Home_Fragment extends Fragment implements EventRVInterface{

    private RecyclerView events_for_2day_rv;
    private TextView home_year_tv;
    private TextView home_month_tv;
    private TextView home_day_tv;

    public Home_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        events_for_2day_rv = view.findViewById(R.id.home_events_RV);
        setEventAdapter();

        home_year_tv = view.findViewById(R.id.home_year_TV);
        home_year_tv.setText(String.valueOf(LocalDate.now().getYear()));

        String abrv_month = String.valueOf(LocalDate.now().getMonth()).substring(0,3);
        home_month_tv = view.findViewById(R.id.home_month_TV);
        home_month_tv.setText(abrv_month);

        int day_num = LocalDate.now().getDayOfMonth();
        String formatted_day = "";
        if (day_num < 10) {
            formatted_day = String.format("%01d", day_num);
        }
        else {
            formatted_day = String.valueOf(day_num);
        }
        home_day_tv = view.findViewById(R.id.home_day_TV);
        home_day_tv.setText(formatted_day);

        return view;
    }

    private void setEventAdapter() {
        //TODO: For whatever reason, the date and time of the event don't show up
        ArrayList<Event> events = Event.eventsForDate(LocalDate.now());
        EventAdapter adapter = new EventAdapter(getActivity().getApplicationContext(), events, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        events_for_2day_rv.setLayoutManager(layoutManager);
        events_for_2day_rv.setAdapter(adapter);
    }

    @Override
    public void onEventClick(int position) {
        //
    }
}