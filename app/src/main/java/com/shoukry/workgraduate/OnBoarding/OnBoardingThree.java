package com.shoukry.workgraduate.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.Login;
import com.shoukry.workgraduate.databinding.ActivityOnBoardingThreeBinding;

public class OnBoardingThree extends AppCompatActivity {


    ActivityOnBoardingThreeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingThreeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnNextPage.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(), Login.class));
        });

    }
}