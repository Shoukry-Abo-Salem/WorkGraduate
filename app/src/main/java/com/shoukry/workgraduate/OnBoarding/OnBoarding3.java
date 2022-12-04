package com.shoukry.workgraduate.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.MainActivity;
import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.ActivityOnBoarding3Binding;

public class OnBoarding3 extends AppCompatActivity {


    ActivityOnBoarding3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoarding3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNextPage.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

    }
}