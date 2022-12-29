package com.shoukry.workgraduate;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.shoukry.workgraduate.databinding.ActivityOnBoardingOneBinding;

public class OnBoardingOne extends AppCompatActivity {

    ActivityOnBoardingOneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnNextPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OnBoardingTow.class));
            }
        });
    }
}
