package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.databinding.ActivityDetailsOrderBinding;
import com.squareup.picasso.Picasso;

public class ActivityDetailsOrder extends AppCompatActivity {

    ActivityDetailsOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");
        String img = getIntent().getStringExtra("img");

        binding.tvNameUser2.setText(name);

        binding.imageBackProvider.setOnClickListener(view -> {
            startActivity(new Intent(getBaseContext(),MainActivity.class));
            finish();
        });
        Picasso.get().load(img).placeholder(R.drawable.imag_hint).into(binding.img);
    }
}