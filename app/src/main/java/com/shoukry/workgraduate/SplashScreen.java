package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.shoukry.workgraduate.OnBoarding.OnBoarding2;
import com.shoukry.workgraduate.OnBoarding.OnBoarding3;
import com.shoukry.workgraduate.OnBoarding.OnBoardingOne;
import com.shoukry.workgraduate.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shared = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        editor = shared.edit();

        int login = shared.getInt("provider",0);
        int boarding = shared.getInt("isBoarding",0);
//        editor.remove("provider");
//        editor.commit();
        Toast.makeText(SplashScreen.this, ""+boarding, Toast.LENGTH_SHORT).show();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (login == 1){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
            }
        },1500);

    }
}