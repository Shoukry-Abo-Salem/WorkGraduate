package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;

import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityCardInfoBinding;

import java.util.ArrayList;

public class CardInfo extends AppCompatActivity {

    ActivityCardInfoBinding binding;
    LoginFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCardInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MapsFragment());

        adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPagerLocation.setAdapter(adapter);

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