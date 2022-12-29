package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.shoukry.workgraduate.databinding.ActivityOnBoardingTowBinding;

public class OnBoardingTow extends AppCompatActivity {

    ActivityOnBoardingTowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingTowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       binding.btnNextPage2.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(), OnBoardingThree.class));
        });

    }
}