package com.example.calendarapp;

import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {

    ImageView event_tag_color;
    TextView event_name_tv;
    TextView event_datetime_tv;

    public EventViewHolder(@NonNull View itemView, EventRVInterface eventRVInterface) {
        super(itemView);

        event_tag_color = itemView.findViewById(R.id.event_color_tag_IV);
        event_name_tv = itemView.findViewById(R.id.event_row_name_TV);
        event_datetime_tv = itemView.findViewById(R.id.event_row_datetime_TV);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventRVInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        eventRVInterface.onEventClick(position);
                    }
                }
            }
        });
    }
}
