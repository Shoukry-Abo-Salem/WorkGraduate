package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shoukry.workgraduate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding = ActivityMainBinding.inflate(getLayoutInflater());




    }
}