package com.example.calendarapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView day_of_month;
    CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        day_of_month = (TextView) itemView.findViewById(R.id.cell_day_TV);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) day_of_month.getText());
    }
}
