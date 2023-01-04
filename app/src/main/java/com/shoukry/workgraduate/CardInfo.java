package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.databinding.ActivityCardInfoBinding;

public class CardInfo extends AppCompatActivity {

    ActivityCardInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackToChoiceCard.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(),ChoiceCard.class));
            finish();
        });

        binding.btnAddOrder.setOnClickListener(View ->{

            startActivity(new Intent(getApplicationContext(), Service.class));
            finish();
        });
    }
}