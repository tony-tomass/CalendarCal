package com.example.calendarapp;

import static com.example.calendarapp.CalendarUtils.daysInWeekArray;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeekView_Fragment extends Fragment
        implements
        CalendarAdapter.OnItemListener, EventRVInterface {

    private TextView month_header_tv;
    private TextView year_header_tv;
    private Button next_week_bt;
    private Button prev_week_bt;
    private Button add_event_bt;
    private RecyclerView calendar_rv;
    private RecyclerView event_list_rv;

    public WeekView_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_week_view, container, false);
        initWidgets(view);
        //event_list_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        return view;
    }

    private void initWidgets(View view) {
        calendar_rv = view.findViewById(R.id.calendar_RV);
        month_header_tv = view.findViewById(R.id.month_header_TV);
        year_header_tv = view.findViewById(R.id.year_header_TV);
        next_week_bt = view.findViewById(R.id.next_week_BT);
        prev_week_bt = view.findViewById(R.id.prev_week_BT);
        add_event_bt = view.findViewById(R.id.add_event_BT);
        //event_list_lv = view.findViewById(R.id.event_list_LV);
        event_list_rv = view.findViewById(R.id.event_list_RV);
        setEventAdapter();
        initListeners();
    }

    private void initListeners() {
        prev_week_bt.setOnClickListener(new View.OnClickListener() {   //Go to Previous Week
            @Override
            public void onClick(View v) {
                CalendarUtils.selected_date = CalendarUtils.selected_date.minusWeeks(1);
                setupCalendar();
            }
        });
        next_week_bt.setOnClickListener(new View.OnClickListener() {   //Go to Next Week
            @Override
            public void onClick(View v) {
                CalendarUtils.selected_date = CalendarUtils.selected_date.plusWeeks(1);
                setupCalendar();
            }
        });
        add_event_bt.setOnClickListener(new View.OnClickListener() {   //Add an Event to a date
            @Override
            public void onClick(View v) {
                AddEvent_Fragment addEvent_fragment = new AddEvent_Fragment();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                //AddEvent_DialogFragment add_event_dialog = new AddEvent_DialogFragment();
                //add_event_dialog.show(manager, "ADD_EVENT");
                manager.beginTransaction()
                        .setCustomAnimations(
                                R.anim.slide_up_anim,
                                R.anim.fade_out_anim,
                                R.anim.fade_in_anim,
                                R.anim.slide_down_anim)   // https://developer.android.com/guide/fragments/animate#java
                        .replace(R.id.fragment_container_view, addEvent_fragment)
                        .addToBackStack("add_event")
                        .commit();
            }
        });
    }

    private void setupCalendar() {
        month_header_tv.setText(CalendarUtils.selected_date.getMonth().toString());
        year_header_tv.setText(String.valueOf(CalendarUtils.selected_date.getYear()));
        ArrayList<LocalDate> days_in_week = daysInWeekArray(CalendarUtils.selected_date);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days_in_week, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7); //7 columns
        calendar_rv.setLayoutManager(layoutManager);
        calendar_rv.setAdapter(calendarAdapter);
        //setEventAdapter();
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
    public void onResume() {
        super.onResume();
        setupCalendar();
    }

    private void setEventAdapter() {
        ArrayList<Event> events = Event.eventsForDate(CalendarUtils.selected_date);
        EventAdapter adapter = new EventAdapter(getActivity().getApplicationContext(), events, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        event_list_rv.setLayoutManager(layoutManager);
        event_list_rv.setAdapter(adapter);
    }

    @Override
    public void onEventClick(int position) {
        //
    }
}