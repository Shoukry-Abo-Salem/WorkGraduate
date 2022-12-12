package com.shoukry.workgraduate;

import static com.shoukry.workgraduate.Register.PROVIDER_REGISTER_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.shoukry.workgraduate.databinding.ActivitySplashScreenBinding;

public class SplashScreen extends AppCompatActivity {

    ActivitySplashScreenBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    public static int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        int isChecked = shared.getInt(String.valueOf(PROVIDER_REGISTER_KEY),0);
//        Toast.makeText(this, ""+isChecked, Toast.LENGTH_SHORT).show();


        sharedPreferences = getSharedPreferences("isSuccess", MODE_PRIVATE);
         num = sharedPreferences.getInt("isSuccess", 0);
//         editor.putInt("isSuccess",15);
//         editor.commit();
//        Toast.makeText(this, ""+num, Toast.LENGTH_SHORT).show();

//              sharedPreferences.getBoolean(PROVIDER_REGISTER_KEY,false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (num == 1){
//                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
//                    finish();
//                }else{
                    startActivity(new Intent(getApplicationContext(),Login.class));
                    finish();
//                }
            }
        },1500);

    }
}