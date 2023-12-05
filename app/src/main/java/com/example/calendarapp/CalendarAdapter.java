package com.example.calendarapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    ArrayList<LocalDate> days;
    OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
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
        if (date == null) {
            holder.day_of_month.setText("");
        }
        else {
            holder.day_of_month.setText(String.valueOf(date.getDayOfMonth()));
            if (date.getMonth() != CalendarUtils.selected_date.getMonth()) {
                holder.day_of_month.setTextColor(Color.LTGRAY);
            }
            if (date.equals(CalendarUtils.selected_date)) {
                holder.parent_view.setBackgroundColor(Color.LTGRAY);
            }
            if (date.equals(LocalDate.now())) {
                holder.day_of_month.setTextColor(Color.RED);
                holder.day_of_month.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }
        ArrayList<Event> events = Event.eventsForDate(date);
        //Log.i("num_of_events", String.valueOf(events.size()));
        if (events.size() > 0) {
            /*
            //There's a whole block of code commented out in calendar_cell.xml that correlates with this comment block
            //Uses a Three-Dot Format
            if (events.size() == 1) {
                holder.event_dot_1.setBackgroundColor(Color.BLUE);
                holder.event_dot_2.setBackgroundColor(Color.TRANSPARENT);
                holder.event_dot_3.setBackgroundColor(Color.TRANSPARENT);
                //holder.event_dot_1.setVisibility(View.VISIBLE);
                //holder.event_dot_2.setVisibility(View.INVISIBLE);
                //holder.event_dot_3.setVisibility(View.INVISIBLE);
            } else if (events.size() == 2) {
                holder.event_dot_1.setBackgroundColor(Color.TRANSPARENT);
                holder.event_dot_2.setBackgroundColor(Color.BLUE);
                holder.event_dot_3.setBackgroundColor(Color.BLUE);
            }
            else {
                holder.event_dot_1.setBackgroundColor(Color.BLUE);
                holder.event_dot_2.setBackgroundColor(Color.BLUE);
                holder.event_dot_3.setBackgroundColor(Color.BLUE);
            }
            //Need quicker way to do this
             */
            //Uses a Three-Bar Format
            if (events.size() > 0) {
                holder.event_dot_3.setBackgroundColor(Color.BLUE);
            }
            if (events.size() > 1) {
                holder.event_dot_2.setBackgroundColor(Color.BLUE);
            }
            if (events.size() > 2) {
                holder.event_dot_1.setBackgroundColor(Color.BLUE);
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
