package com.example.calendarapp;

import android.view.View;
import android.widget.ImageView;
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
    public final TextView day_of_month;
    public final ImageView event_dot_1;
    public final ImageView event_dot_2;
    public final ImageView event_dot_3;
    CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);
        day_of_month = itemView.findViewById(R.id.cell_day_TV);
        event_dot_1 = itemView.findViewById(R.id.event_dot_one_IV);
        event_dot_2 = itemView.findViewById(R.id.event_dot_two_IV);
        event_dot_3 = itemView.findViewById(R.id.event_dot_three_IV);
        this.onItemListener = onItemListener;
        this.days = days;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), days.get(getAdapterPosition()));
    }
}
