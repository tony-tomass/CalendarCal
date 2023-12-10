package com.example.calendarapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Profile_Fragment extends Fragment {

    ImageView imageView;
    EditText ufName, ufEmail, ufMajor, ufClassification;
    Button saveButton;

    public Profile_Fragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = view.findViewById(R.id.imageView3);
        ufName = view.findViewById(R.id.uf_name);
        ufEmail = view.findViewById(R.id.uf_email);
        ufMajor = view.findViewById(R.id.uf_major);
        ufClassification = view.findViewById(R.id.uf_classification);
        saveButton = view.findViewById(R.id.save_button);

        getActivity();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        String name = sharedPref.getString("Name", "Full Name");
        String email = sharedPref.getString("Email", "Email");
        String major = sharedPref.getString("Major", "Major");
        String classification = sharedPref.getString("Classification", "Classification");

        ufName.setText(name);
        ufEmail.setText(email);
        ufMajor.setText(major);
        ufClassification.setText(classification);

        saveButton.setOnClickListener(saveProfile);
        return view;
    }

    View.OnClickListener saveProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(validInputs()){
                SharedPreferences sharedPref = getActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("Name", ufName.getText().toString());
                editor.putString("Email", ufEmail.getText().toString());
                editor.putString("Major", ufMajor.getText().toString());
                editor.putString("Classification", ufClassification.getText().toString());
                editor.apply();

                Toast.makeText(getActivity().getApplicationContext(), "Profile saved successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public boolean validInputs(){
        if (ufName.getText().toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Name is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ufEmail.getText().toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ufMajor.getText().toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Major is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ufClassification.getText().toString().isEmpty()) {
            Toast.makeText(getActivity().getApplicationContext(), "Classification is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}