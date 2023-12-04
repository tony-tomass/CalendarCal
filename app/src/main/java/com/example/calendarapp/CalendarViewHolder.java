package com.example.calendarapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final ArrayList<LocalDate> days;
    public final View parent_view = itemView.findViewById(R.id.parentView);
    TextView day_of_month;
    CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);
        day_of_month = (TextView) itemView.findViewById(R.id.cell_day_TV);
        this.onItemListener = onItemListener;
        this.days = days;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
