package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.shoukry.workgraduate.databinding.ActivityOnBoardingTowBinding;

public class OnBoardingTow extends AppCompatActivity {

    ActivityOnBoardingTowBinding binding;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = ActivityOnBoardingTowBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_on_boarding_tow);

        button = findViewById(R.id.btn_Next_Page2);
       button.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(), OnBoardingThree.class));
            finish();
        });

    }
}