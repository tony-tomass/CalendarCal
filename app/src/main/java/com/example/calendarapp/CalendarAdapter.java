package com.example.calendarapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    ArrayList<LocalDate> days;
    OnItemListener onItemListener;
    private View view;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (days.size() > 15) {   //For setting up Month view
            layoutParams.height = (int) (parent.getHeight() * 0.166666); //Makes each cell 1/6th of the full view
        }
        else {   //For setting up Week view
            layoutParams.height = parent.getHeight();
        }
        return new CalendarViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        LocalDate date = days.get(position);
        TextView day_tv = holder.day_of_month;
        View parent_view = holder.parent_view;
        if (date == null) {
            day_tv.setText("");
        }
        else {

            day_tv.setText(String.valueOf(date.getDayOfMonth()));
            if (date.getMonth() != CalendarUtils.selected_date.getMonth()) {   //In and Out Dates
                day_tv.setTextColor(
                        ResourcesCompat.getColor(view.getResources(), R.color.theme_secondary_accent1, null));
                parent_view.setBackgroundColor(
                        ResourcesCompat.getColor(view.getResources(), R.color.theme_background_accent1, null));
            }
            else {
                day_tv.setTextColor(
                        ResourcesCompat.getColor(view.getResources(), R.color.theme_secondary_accent2, null));
            }

            if (date.equals(LocalDate.now())) {   //Current Date
                day_tv.setTextColor(
                        ResourcesCompat.getColor(view.getResources(), R.color.theme_secondary, null));
                parent_view.setBackgroundColor(
                        ResourcesCompat.getColor(view.getResources(), R.color.theme_primary_accent1, null));
                day_tv.setTypeface(Typeface.DEFAULT_BOLD);
            }

            if (date.equals(CalendarUtils.selected_date)) {   //Selected Date
                if (CalendarUtils.selected_date.equals(LocalDate.now())) {   //Selected Date = Current Date
                    day_tv.setTextColor(
                            ResourcesCompat.getColor(view.getResources(), R.color.theme_secondary, null));
                    parent_view.setBackgroundColor(
                            ResourcesCompat.getColor(view.getResources(), R.color.theme_secondary_accent2, null));
                    day_tv.setTypeface(Typeface.DEFAULT_BOLD);
                }
                else {
                    day_tv.setTextColor(
                            ResourcesCompat.getColor(view.getResources(), R.color.theme_background, null));
                    parent_view.setBackgroundColor(
                            ResourcesCompat.getColor(view.getResources(), R.color.theme_secondary_accent2, null));
                }
            }

        }
        ArrayList<Event> events = Event.eventsForDate(date);
        //Log.i("num_of_events", String.valueOf(events.size()));
        if (events.size() > 0) {
            holder.event_dot_3.setBackgroundColor(events.get(0).getTag_color());
            if (events.size() > 1) {
                holder.event_dot_2.setBackgroundColor(events.get(1).getTag_color());
            }
            if (events.size() > 2) {
                holder.event_dot_1.setBackgroundColor(events.get(2).getTag_color());
            }
        }
        else {
            holder.event_dot_1.setBackgroundColor(Color.TRANSPARENT);
            holder.event_dot_2.setBackgroundColor(Color.TRANSPARENT);
            holder.event_dot_3.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
