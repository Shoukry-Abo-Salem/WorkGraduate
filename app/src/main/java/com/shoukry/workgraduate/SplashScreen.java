package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.shoukry.workgraduate.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("isSuccess", MODE_PRIVATE);
        int num = sharedPreferences.getInt("isSuccess", 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (num == 1){
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(),Register.class));
                    finish();
                }
            }
        },1500);

    }
}