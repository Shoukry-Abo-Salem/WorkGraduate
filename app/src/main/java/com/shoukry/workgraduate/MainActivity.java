package com.shoukry.workgraduate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    LoginFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new ServiceFragment());
        fragmentArrayList.add(new OrdersFragment());


        adapter = new LoginFragmentAdapter(this,fragmentArrayList);
        binding.viewPagerHome.setAdapter(adapter);



        binding.bottomNavHome.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int  itemId = item.getItemId();

                if (itemId == R.id.service){
                    binding.viewPagerHome.setCurrentItem(0);
                }else if (itemId == R.id.orders){
                    binding.viewPagerHome.setCurrentItem(1);
                }else if (itemId == R.id.user){
                    binding.viewPagerHome.setCurrentItem(2);
                }


                return false;
            }
        });

        binding.viewPagerHome.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.bottomNavHome.getMenu().getItem(position).setChecked(false);
            }
        });
        binding.viewPagerHome.setUserInputEnabled(false);
    }
}