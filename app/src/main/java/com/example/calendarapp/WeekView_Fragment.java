package com.example.calendarapp;

import static com.example.calendarapp.CalendarUtils.daysInWeekArray;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private Button events_for_today_bt;
    private Button back2_month_view_bt;
    private RecyclerView calendar_rv;
    private RecyclerView event_list_rv;
    private ImageView no_events_iv;
    private TextView no_events_tv;
    private ArrayList<Event> events;

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
        setupCalendar();
        //Log.i("LOAD", "Events loaded: " + Event.events_list.size());
        //event_list_rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        return view;
    }

    private void initWidgets(View view) {
        events = Event.eventsForDate(CalendarUtils.selected_date);
        calendar_rv = view.findViewById(R.id.calendar_RV);
        month_header_tv = view.findViewById(R.id.month_header_TV);
        year_header_tv = view.findViewById(R.id.year_header_TV);
        next_week_bt = view.findViewById(R.id.next_week_BT);
        prev_week_bt = view.findViewById(R.id.prev_week_BT);
        events_for_today_bt = view.findViewById(R.id.add_event_BT);
        back2_month_view_bt = view.findViewById(R.id.back2_month_view_BT);
        event_list_rv = view.findViewById(R.id.event_list_RV);

        no_events_iv = view.findViewById(R.id.no_event_wview_IV);
        no_events_tv = view.findViewById(R.id.no_event_wview_TV);

        initListeners();
        //checkEventAmount();

        String back_text = " < " + CalendarUtils.selected_date.getMonth().getValue() + "/" + CalendarUtils.selected_date.getYear();
        back2_month_view_bt.setText(back_text);


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
        back2_month_view_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
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
        events = Event.eventsForDate(CalendarUtils.selected_date);
        if (events.size() == 0) {
            no_events_iv.setVisibility(View.VISIBLE);
            no_events_tv.setVisibility(View.VISIBLE);
            event_list_rv.setVisibility(View.INVISIBLE);
        }
        else {
            no_events_iv.setVisibility(View.INVISIBLE);
            no_events_tv.setVisibility(View.INVISIBLE);
            event_list_rv.setVisibility(View.VISIBLE);
            String events_for_2day = "Events for " + CalendarUtils.selected_date.format(CalendarUtils.dateFormatter_MED);
            events_for_today_bt.setText(events_for_2day);

            EventAdapter adapter = new EventAdapter(getActivity().getApplicationContext(), events, this);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            event_list_rv.setLayoutManager(layoutManager);
            event_list_rv.setAdapter(adapter);
        }
    }

    public void onEventClick(int position) {
        //
    }
}