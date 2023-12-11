package com.example.calendarapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;

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

    public static String[] event_conProv_colNames = new String[]{
            "_id",
            EventContentProvider.COL1_NAME,
            EventContentProvider.COL2_NAME,
            EventContentProvider.COL3_NAME,
            EventContentProvider.COL4_NAME,
    };

    public static Cursor cursor;
    private int tag_color;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public Event(String name, LocalDate date, LocalTime time, int tag_color) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.tag_color = tag_color;
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

    public int getTag_color() {
        return tag_color;
    }

    public void setTag_color(int tag_color) {
        this.tag_color = tag_color;
    }

    public static void saveEventData(Context context, String name, String date, String time, int tag_color) {
        ContentValues new_vals = new ContentValues();
        new_vals.put(EventContentProvider.COL1_NAME, name);
        new_vals.put(EventContentProvider.COL2_NAME, date);
        new_vals.put(EventContentProvider.COL3_NAME, time);
        new_vals.put(EventContentProvider.COL4_NAME, tag_color);
        context.getContentResolver().insert(EventContentProvider.CONTENT_URI, new_vals);

        Log.i("SAVED", "Event saved");
        Log.i("COLOR_INT", String.valueOf(tag_color));
    }

    public static void loadEventData(Context context) {
        cursor = context.getContentResolver()
                .query(EventContentProvider.CONTENT_URI,
                        event_conProv_colNames,
                        null, null, null);

        while (cursor.moveToNext()) {
            String query_name = cursor.getString(1);
            //Log.i("QUERIED_NAME", query_name);

            String query_date = cursor.getString(2);
            LocalDate parsed_date = LocalDate.parse(query_date);
            //Log.i("QUERIED_DATE", parsed_date.toString());

            String query_time = cursor.getString(3);
            LocalTime parsed_time = LocalTime.parse(query_time);
            //String fu = parsed_time.format(CalendarUtils.timeFormatter_12HR);
            //Log.i("QUERIED_TIME", fu);

            int query_color_id = cursor.getInt(4);

            Event event = new Event(query_name, parsed_date, parsed_time, query_color_id);
            Event.events_list.add(event);
        }
    }

}
