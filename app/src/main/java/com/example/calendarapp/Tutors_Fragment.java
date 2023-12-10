package com.example.calendarapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Tutors_Fragment extends Fragment {

    RecyclerView recyclerView;
    PersonAdapter adapter;
    List<Person> personList;

    public Tutors_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutors, container, false);

        recyclerView = view.findViewById(R.id.people_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        personList = new ArrayList<>();
        personList.add(new Person("Ryan Truong", "Computer Science", R.drawable.ic_launcher_background));
        personList.add(new Person("Tony Nguyen", "Computer Science", R.drawable.ic_launcher_background));
        personList.add(new Person("Esteban", "Professor", R.drawable.ic_launcher_background));


        adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}