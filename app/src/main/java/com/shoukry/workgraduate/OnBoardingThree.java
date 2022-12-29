package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.shoukry.workgraduate.Login;
import com.shoukry.workgraduate.databinding.ActivityOnBoardingThreeBinding;

public class OnBoardingThree extends AppCompatActivity {


    ActivityOnBoardingThreeBinding binding;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingThreeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shared = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = shared.edit();

        binding.btnNextPage3.setOnClickListener(View ->{
            editor.putInt("isBoarding",1);
            editor.commit();
            startActivity(new Intent(getApplicationContext(), Login.class));
        });

    }
}