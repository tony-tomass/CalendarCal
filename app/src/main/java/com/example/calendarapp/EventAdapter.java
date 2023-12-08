package com.example.calendarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
        //https://howtodoinjava.com/java/date-time/java8-datetimeformatter-example/
        DateTimeFormatter date_formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        DateTimeFormatter time_formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        holder.event_datetime_tv.setText("" + events.get(position).getDate().format(date_formatter) + " at " + LocalTime.now().format(time_formatter));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
