package com.example.calendarapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
            layoutParams.height = (int) parent.getHeight();
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
        }
        /*
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getMonth() != CalendarUtils.selected_date.getMonth()) {
                holder.parent_view.
            }
        }

         */
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
