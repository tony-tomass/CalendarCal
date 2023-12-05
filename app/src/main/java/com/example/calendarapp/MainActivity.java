package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentManager frag_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://developer.android.com/guide/fragments/transactions

        frag_manager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            frag_manager.beginTransaction()
                    .replace(R.id.fragment_container_view, new MonthViewFragment())
                    .addToBackStack("cal_month_view")
                    .commit();
        }
    }
}