package com.shoukry.workgraduate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.shoukry.workgraduate.AdapterFragments.LoginFragmentAdapter;
import com.shoukry.workgraduate.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    LoginFragmentAdapter adapter;
    Button login;
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

        binding.navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                login = findViewById(R.id.btn_Login);

                if (itemId == R.id.provider){
                    binding.viewPager.setCurrentItem(0);
                    login.setOnClickListener(view ->{

                    });
                }else if (itemId == R.id.customer){
                    binding.viewPager.setCurrentItem(1);
                    login.setOnClickListener(view ->{

                    });
                }
                return false;
            }
        });

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.navBar.getMenu().getItem(position).setChecked(true);
            }
        });

    }
}