package com.example.calendarapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PersonViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView nameTextView;
    TextView infoTextView;

    public PersonViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.person_image);
        nameTextView = itemView.findViewById(R.id.person_name);
        infoTextView = itemView.findViewById(R.id.person_info);
    }
}
