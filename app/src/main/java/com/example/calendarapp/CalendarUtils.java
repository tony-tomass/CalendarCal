package com.example.calendarapp;

import android.util.Log;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarUtils {

    public static LocalDate selected_date;

    public static ArrayList<LocalDate> daysInMonthArray(LocalDate localDate) {
        ArrayList<LocalDate> days_in_month_array = new ArrayList<>();
        YearMonth year_month = YearMonth.from(localDate);   //Gets year and month
        int days_in_month = year_month.lengthOfMonth();   //Gets number of days in above month
        //Log.i("daysinmonth", String.valueOf(days_in_month));
        LocalDate first_of_month = localDate.withDayOfMonth(1);   //Gets date of first day of month
        int day_of_week = first_of_month.getDayOfWeek().getValue() % 7;   //Gets what day of week first day appears; mod 7 is used b/c without it, a whole row gets skipped if the first day is on Sunday (array starts at 0 but getDayOfWeek starts at 1)
        //Log.i("dayofweek", String.valueOf(day_of_week));

        for (int i = 1; i <= 42; i++) {   //42 from 7 days a week and 6 max rows of weeks for one month
            if (i <= day_of_week || i > days_in_month + day_of_week) {   //If after the first or last day of the month
                //LocalDate difference = first_of_month.minusDays(day_of_week + 1);
                LocalDate date = first_of_month.minusDays(day_of_week + 1).plusDays(i);   //Generates the in and out dates
                //Log.i("date", date.toString());
                days_in_month_array.add(date);
            }
            else {
                days_in_month_array.add(LocalDate.of(selected_date.getYear(), selected_date.getMonth(), i - day_of_week));
            }
        }

        return days_in_month_array;
    }

    public static ArrayList<LocalDate> daysInWeekArray(LocalDate selectedDate) {
        ArrayList<LocalDate> days = new ArrayList<>();
        LocalDate current_date = sundayForDate(selectedDate);
        LocalDate endDate = current_date.plusWeeks(1);

        while (current_date.isBefore(endDate)) {
            days.add(current_date);
            current_date = current_date.plusDays(1);
        }

        return days;
    }

    private static LocalDate sundayForDate(LocalDate selectedDate) {   //Looks for week at start of Sunday, without it, week views will start on the first of the month without getting the days before
        LocalDate oneWeekAgo = selectedDate.minusWeeks(1);
        while (selectedDate.isAfter(oneWeekAgo)) {
            if (selectedDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return selectedDate;
            }
            selectedDate = selectedDate.minusDays(1);
        }
        return null;
    }

    public static String monthYearFromDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return localDate.format(formatter);
    }

    public static String formatDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return localDate.format(formatter);
    }

    public static String formatTime(LocalTime localTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        return localTime.format(formatter);
    }
}
