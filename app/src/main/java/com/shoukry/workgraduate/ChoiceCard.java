package com.shoukry.workgraduate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import com.shoukry.workgraduate.databinding.ActivityChoiceCardBinding;

public class ChoiceCard extends AppCompatActivity {

    ActivityChoiceCardBinding binding;
    public final int GALLERY_REQ_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChoiceCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnBackToFirst.setOnClickListener(View ->{
            startActivity(new Intent(getBaseContext(), Service.class));
            finish();
        });

        binding.editeTxtDetailsProblem.getText().toString();

        binding.btnAddImage.setOnClickListener(View ->{
            Intent iGallery = new Intent(Intent.ACTION_PICK);
            iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(iGallery,GALLERY_REQ_CODE);
        });

        binding.btnNext.setOnClickListener(View ->{
            startActivity(new Intent(getApplicationContext(),CardInfo.class)
                    .putExtra("details",binding.editeTxtDetailsProblem.getText().toString()));
        });


//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data;) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (requestCode == GALLERY_REQ_CODE){
//                binding.civUserImage.setImageURI(data.getData());
//            }
//        }
    }
}