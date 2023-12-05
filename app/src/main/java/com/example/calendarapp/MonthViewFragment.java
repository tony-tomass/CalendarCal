package com.example.calendarapp;

import static com.example.calendarapp.CalendarUtils.daysInMonthArray;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MonthViewFragment extends Fragment implements CalendarAdapter.OnItemListener{

    private TextView month_header_tv;
    private TextView year_header_tv;
    private Button next_month_bt;
    private Button prev_month_bt;
    private RecyclerView calendar_rv;
    FragmentManager child_frag_manager;

    public MonthViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalendarUtils.selected_date = LocalDate.now();
        child_frag_manager = getChildFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_month_view, container, false);
        initWidgets(view);
        return view;
    }

    private void initWidgets(View view) {
        calendar_rv = view.findViewById(R.id.calendar_RV);
        month_header_tv = view.findViewById(R.id.month_header_TV);
        year_header_tv = view.findViewById(R.id.year_header_TV);
        next_month_bt = view.findViewById(R.id.next_month_BT);
        prev_month_bt = view.findViewById(R.id.prev_month_BT);
        initListeners();
    }

    private void initListeners() {
        prev_month_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selected_date = CalendarUtils.selected_date.minusMonths(1);
                setupCalendar();
            }
        });
        next_month_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUtils.selected_date = CalendarUtils.selected_date.plusMonths(1);
                setupCalendar();
            }
        });
    }

    private void setupCalendar() {
        month_header_tv.setText(CalendarUtils.selected_date.getMonth().toString());
        year_header_tv.setText(String.valueOf(CalendarUtils.selected_date.getYear()));
        ArrayList<LocalDate> days_in_month = daysInMonthArray(CalendarUtils.selected_date);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days_in_month, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 7); //7 columns
        calendar_rv.setLayoutManager(layoutManager);
        calendar_rv.setAdapter(calendarAdapter);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selected_date = date;
        //startActivity(new Intent(getActivity().getApplicationContext(), WeekViewActivity.class));
        WeekViewFragment weekViewFragment = new WeekViewFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.fragment_container_view, weekViewFragment)
                .addToBackStack("cal_week_view")
                .commit();
        setupCalendar();
    }

    @Override
    public void onResume(){
        super.onResume();
        setupCalendar();
    }
}