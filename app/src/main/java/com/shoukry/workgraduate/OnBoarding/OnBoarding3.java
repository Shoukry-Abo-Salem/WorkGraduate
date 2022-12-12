package com.shoukry.workgraduate.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.shoukry.workgraduate.MainActivity;
import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.ActivityOnBoarding3Binding;

public class OnBoarding3 extends AppCompatActivity {


    ActivityOnBoarding3Binding binding;
    SharedPreferences shared;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoarding3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shared = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = shared.edit();

        binding.btnNextPage.setOnClickListener(view -> {
            editor.putInt("isBoarding",1);
            editor.commit();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

    }
}