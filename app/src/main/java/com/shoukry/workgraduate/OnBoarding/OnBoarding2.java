package com.shoukry.workgraduate.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.ActivityOnBoarding2Binding;

public class OnBoarding2 extends AppCompatActivity {

    ActivityOnBoarding2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoarding2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNextPage.setOnClickListener(view ->{

            startActivity(new Intent(getApplicationContext(),OnBoarding3.class));
        });
    }
}