package com.example.calendarapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

//Creating a RecyclerView
//https://developer.android.com/develop/ui/views/layout/recyclerview
public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    Context context;
    ArrayList<Event> events;
    EventRVInterface eventRVInterface;

    public EventAdapter(Context context, ArrayList<Event> events, EventRVInterface eventRVInterface) {
        this.context = context;
        this.events = events;
        this.eventRVInterface = eventRVInterface;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.event_list_row, parent, false), eventRVInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        //holder.event_tag_color.setBackground(events.get);
        holder.event_name_tv.setText(events.get(position).getName());
        String date = events.get(position).getDate().format(CalendarUtils.dateFormatter_MED);
        String time = events.get(position).getTime().format(CalendarUtils.timeFormatter_12HR);
        String date_time = date + " at " + time;
        holder.event_datetime_tv.setText(date_time);
        //holder.event_datetime_tv.setTextColor(Color.WHITE);
        holder.event_tag_color.setBackgroundColor(events.get(position).getTag_color());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
