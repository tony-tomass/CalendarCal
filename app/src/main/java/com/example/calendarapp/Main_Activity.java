package com.example.calendarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

//All about Fragments
//https://developer.android.com/guide/fragments#getting-started
public class Main_Activity extends AppCompatActivity{

    FragmentManager frag_manager;

    BottomNavigationView btm_nav_bar_bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Event.loadEventData(getApplicationContext());

        //https://developer.android.com/guide/fragments/transactions
        frag_manager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            changeFragment(new MonthView_Fragment());
        }

        btm_nav_bar_bnv = findViewById(R.id.bottom_nav_bar_BNV);
        btm_nav_bar_bnv.getMenu().findItem(R.id.calendar_bottom_nav_BT).setChecked(true);
        btm_nav_bar_bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                //No switch case => https://stackoverflow.com/questions/9092712/switch-case-statement-error-case-expressions-must-be-constant-expression
                if (itemId == R.id.home_bottom_nav_BT) {
                    changeFragment(new Home_Fragment());
                }
                else if (itemId == R.id.calendar_bottom_nav_BT) {
                    changeFragment(new MonthView_Fragment());
                }
                else if (itemId == R.id.tutors_bottom_nav_BT) {
                    changeFragment(new Tutors_Fragment());
                }
                else if (itemId == R.id.profile_bottom_nav_BT) {
                    changeFragment(new Profile_Fragment());
                }
                return true;
            }
        });
    }

    private void changeFragment(Fragment fragment) {
        frag_manager.beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)
                .commit();
    }

}