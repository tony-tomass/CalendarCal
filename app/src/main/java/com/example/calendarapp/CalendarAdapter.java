package com.example.calendarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    ArrayList<String> days_of_month;
    OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<String> days_of_month, OnItemListener onItemListener) {
        this.days_of_month = days_of_month;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666); //Makes each cell 1/6th of the full view
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        holder.day_of_month.setText(days_of_month.get(position));
    }

    @Override
    public int getItemCount() {
        return days_of_month.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);
    }
}
