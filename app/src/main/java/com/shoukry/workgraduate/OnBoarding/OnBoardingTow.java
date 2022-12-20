package com.shoukry.workgraduate.OnBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.databinding.ActivityOnBoardingTowBinding;

public class OnBoardingTow extends AppCompatActivity {

    ActivityOnBoardingTowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingTowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNextPage.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(),OnBoardingThree.class));
            finish();
        });

    }
}