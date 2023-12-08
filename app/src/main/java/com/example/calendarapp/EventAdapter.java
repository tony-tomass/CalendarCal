package com.example.calendarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {


    public EventAdapter(@NonNull Context context, List<Event> event_list) {
        super(context, 0, event_list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }
        TextView event_name_tv = (TextView) convertView.findViewById(R.id.event_cell_TV);
        String event_name = event.getName() + " " + CalendarUtils.formatTime(event.getTime());
        event_name_tv.setText(event_name);
        return convertView;
    }
}
