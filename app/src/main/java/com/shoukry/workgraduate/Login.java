package com.shoukry.workgraduate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new LoginFragment());
        fragmentArrayList.add(new LoginFragment());

        adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPager.setAdapter(adapter);



        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });

    }
}