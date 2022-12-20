package com.shoukry.workgraduate.OnBoarding;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.shoukry.workgraduate.R;
import com.shoukry.workgraduate.databinding.ActivityOnBoardingOneBinding;

public class OnBoardingOne extends AppCompatActivity {

    ActivityOnBoardingOneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNextPage.setOnClickListener(View ->{
//            startActivity(new Intent(getApplicationContext(),));
        });
    }
}
