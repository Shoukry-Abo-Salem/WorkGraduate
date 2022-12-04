package com.shoukry.workgraduate.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.ActivityOnBoarding1Binding;

public class OnBoarding1 extends AppCompatActivity {

    ActivityOnBoarding1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoarding1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNextPage.setOnClickListener(view ->{
            startActivity(new Intent(getApplicationContext(),OnBoarding2.class));

        });
    }
}