package com.example.calendarapp;

import android.graphics.Color;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    public static ArrayList<Event> events_list = new ArrayList<>();
    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < events_list.size(); i++) {
            if (events_list.get(i).getDate().equals(date)) {
                events.add(events_list.get(i));
            }
        }
        return events;
    }

    private Color tag_color;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Color getTag_color() {
        return tag_color;
    }

    public void setTag_color(Color tag_color) {
        this.tag_color = tag_color;
    }
}
