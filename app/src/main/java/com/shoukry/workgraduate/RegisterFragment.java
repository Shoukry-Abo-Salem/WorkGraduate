package com.shoukry.workgraduate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoukry.workgraduate.databinding.ActivityRegisterBinding;
import com.shoukry.workgraduate.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

//FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
//        binding.textSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(this,Login.class));
//            }
//        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}